package com.example.service;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component

public class SpringResourceHolder implements BeanFactoryPostProcessor, ResourceLoaderAware {

    protected MetadataReaderFactory metadataReaderFactory;
    protected ResourcePatternResolver resourcePatternResolver;
    private ConfigurableListableBeanFactory beanFactory;

    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
    public MetadataReaderFactory getMetadataReaderFactory() {
        return metadataReaderFactory;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)  {
        this.beanFactory=beanFactory;
    }


    public Resource[] getResources(String packageName)
    {
        try {
            return resourcePatternResolver.getResources(getLocationPattern(packageName));
        } catch (IOException e) {
            System.out.println(e);
        }
        return new Resource[0];
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }


    /**
     * 获取服务的代理对象
     * @param serviceClass
     * @param beanFactory
     * @return
     */
    protected Object getServiceProxy(Class<?> serviceClass, ConfigurableListableBeanFactory beanFactory)  {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addInterface(serviceClass);
        return proxyFactory.getProxy(beanFactory.getBeanClassLoader());
    }



    /**
     * 获取资源的路径
     * @return
     */
    protected String getLocationPattern(String packageName) {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(packageName) + "/**/*.class";
    }


    /**
     * spring ByName方式注入的名称
     * @param serviceClass
     * @return
     */
    protected String getServiceName(Class<?> serviceClass) {
        return StringUtils.uncapitalize(serviceClass.getSimpleName());
    }

}
