package com.example.service;

import com.alibaba.fastjson.JSON;
import com.example.annotion.MatGroup;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 测试反射注解
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/16 16:14
 */
@Service
public class TestService implements ApplicationContextAware {

    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    private static ApplicationContext appCtx;

    List<String> stringList;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

    //bean测试
    public String demo(String packageName) throws IOException {
        if (stringList==null){
            stringList=new ArrayList<>();
        }
        stringList.add(packageName);
        SpringResourceHolder springResourceHolder = appCtx.getBean(SpringResourceHolder.class);
        Resource[] resources = resourcePatternResolver.getResources("classpath*:com.example.service/**/*.class");
        MetadataReader metadataReader;
        for (Resource resource:resources){
            metadataReader = springResourceHolder.getMetadataReaderFactory().getMetadataReader(resource);
            String className = metadataReader.getClassMetadata().getClassName();
            String[] beanNames = className.split("\\.");
            String beanName = beanNames[beanNames.length-1].substring(0,1).toLowerCase().concat(beanNames[beanNames.length-1].substring(1));
            springResourceHolder.getBeanFactory().getBean(beanName);
        }
        for (String str : appCtx.getBeanDefinitionNames()){
            System.out.println(str);
        }
        return String.valueOf(stringList.size());
    }

    public String checkMatGroup(Object object) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields){
            if (field.isAnnotationPresent(MatGroup.class)){
                String prefix = "get";
                String name = field.getName();
                field.setAccessible(true);
                Object[] arr = null;
                Object obj = field.get(object);
                /**
                 * if (field.get(obj) instanceof Collection){
                 *                     arr = ((Collection) obj).toArray();
                 *                 }
                 *                 if (field.getType().isArray()){
                 *                     arr = (Object[]) obj;
                 *                 }
                 *                  if (null!=arr){
                 *                     for (Object object1 : arr){
                 *                         checkMatGroup(object1);
                 *                     }
                 *                 }**/

                Method method = object.getClass().getMethod(prefix+name.substring(0,1).toUpperCase()+name.substring(1));

                Object invoke = method.invoke(object);
                if (invoke instanceof String ){
                    MatGroup matGroup = field.getAnnotation(MatGroup.class);
                    if (!((String) invoke).startsWith(matGroup.matGroup())){
                        stringBuilder.append(invoke).append(matGroup.message()).append(matGroup.matGroup()).append(";");
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    public String getAttr(Object object) throws IllegalAccessException {
        Map<String,List<Integer>> replaceGroupMap = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        String replaceGroup = null;
        Integer usePercent = null;
        for (Field field : fields){
            field.setAccessible(true);
            if ("replaceGroup".equals(field.getName())){
                replaceGroup = (String) field.get(object);
            }

            if ("userPercent".equals(field.getName())){
               usePercent = NumberUtils.toInt(String.valueOf(field.get(object)).split(".")[0]);
            }
        }
        if (CollectionUtils.isEmpty(replaceGroupMap.get(replaceGroup))){
            List<Integer> usePercentList = new ArrayList<>();
            usePercentList.add(usePercent);
            replaceGroupMap.put(replaceGroup,usePercentList);
        }
        else {
            replaceGroupMap.get(replaceGroup).add(usePercent);
        }

        return "";
    }

    private void learn(){
        Map<String,String> testMap = new HashMap<>();
        testMap.get("");
    }

    public <T> List<T> testVar(List list,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        List<T> target = new ArrayList<>();
        T obj = clazz.newInstance();
        target.add(obj);
        for (Field field : obj.getClass().getDeclaredFields()){
            System.out.println(field);
        }

        return target;
    }

    public  <T> List<T> copyProperties(List<?> source,Class<T> targetType) {
        if (CollectionUtils.isEmpty(source)){
            throw new RuntimeException("列表不能为空");
        }
        List<T> result = new ArrayList<>();
        source.forEach(s -> {
            T obj = null;
            try {
                obj = targetType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj==null){
                throw new RuntimeException("对象转换失败");
            }
            BeanUtils.copyProperties(s,obj);
            result.add(obj);
        });

        return result;
    }

    public  <T> List<T> copyProperties(List<?> source, Map<String,String> propertyMap,Class<T> targetType){
        List<T> target = copyProperties(source,targetType);
        for (int index = 0;index<target.size();index++){
            int finalIndex = index;
            propertyMap.forEach((k, v)->{
                T t = getProperty(source.get(finalIndex),k);
                setProperty(target.get(finalIndex),v,t);
            });
        }
        return target;

    }

    private  <T> T getProperty(Object obj, String property) {
        Field field;
        for (Field field1 : obj.getClass().getDeclaredFields()){
            System.out.println(field1.getName());
        }
        try {
            field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(String.format("源对象中没有属性[%s]！",property));
        }
        String jsonString;
        try {
            jsonString = JSON.toJSONString(field.get(obj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("获取属性失败！");
        }
        return JSON.parseObject(jsonString, (Type) field.getType());
    }

    private  <T> void  setProperty(Object obj, String property, T propertyValue) {
        Field field;
        try {
            field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(String.format("源对象中没有属性[%s]！",property));
        }
        try {
            field.set(obj,propertyValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("设置属性失败！");
        }

    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("a","b","c","d");
        System.out.println(test.subList(0,3));

    }

    public static <T> String tets(Class<T> clazz){
        if (clazz.isInterface()){
//            return new Collection<>();
           }
        return "fou";
    }



}
