package com.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *测试restTemplate
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/24 10:15
 */
@Service
public class RestTemplateService {
    RestTemplate restTemplate = new RestTemplate();

    public String getToken(){
        String url ="http://pfgatewayt.transsion.com:9099/service-pt-unified-auth/pt-unified-auth/login";
        JSONObject jsonObject =JSON.parseObject("{\"username\":\"18645652\",\"pwd\":\"c2t5bGFuZCM5NzA2MTg=\"}");
        JSONObject token = JSON.parseObject(restTemplate.postForEntity(url,jsonObject,String.class).getBody());
        return JSON.parseObject(token.getString("data")).getString("token");

    }

    public String restTemplatePost(){
        String url = "http://localhost:7005/bom-arc/replaceRuleManage/query";
        JSONObject jsonObject = JSON.parseObject("{\"count\":true,\"current\":0,\"param\":{},\"size\":10}");
        String obj = "{\"count\":true,\"current\":0,\"param\":{},\"size\":10}";
        HttpHeaders headers = new HttpHeaders();
        String token = getToken();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",token);
        HttpEntity httpEntity = new HttpEntity<>(obj,headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,httpEntity,String.class);

        ResponseEntity<String> entity1 = restTemplate.postForEntity(url,jsonObject,String.class);
        System.out.println(entity.getHeaders());
        return entity.getBody();
    }

    public String restTemplateGet() {

        String url = "http://10.250.112.182:11000/base/dictionary/codes?appCode=pdc&state=1";
        ResponseEntity<String> entity = restTemplate.getForEntity(url,String.class);
        return entity.getBody();
    }


}
