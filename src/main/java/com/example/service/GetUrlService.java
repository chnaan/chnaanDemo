package com.example.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *测试Url
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/22 14:25
 */
@Service
public class GetUrlService {

    public String urlTest() throws IOException {
        URL url = new URL("http://baidu.com");
        JSONObject jsonObject = getUrl(url);
        return jsonObject.toJSONString();
    }

    public String getResponse() throws IOException {
        URL url = new URL("http://10.250.112.193:5000/v2/_catalog");
        List<String> imageList = new ArrayList<>();
        JSONObject jsonObject = getUrl(url);

        for (Object str : (Collection)jsonObject.get("repositories")){
            imageList.add((String) str);
        }

        List<String> imagesInfoList = new ArrayList<>();
        for (String str : imageList){
            URL url1 = new URL("http://10.250.112.193:5000/v2/" + str+ "/tags/list");
            JSONObject jsonObject1 = getUrl(url1);
            for (Object object : (Collection) jsonObject1.get("tags")){
                imagesInfoList.add(str+":"+(String)object);
            }

        }
        imagesInfoList.sort(String::compareTo);
        imagesInfoList.forEach(s->System.out.println(s));
        return imagesInfoList.toString();
    }

    private JSONObject getUrl(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection connection = null;
        if(urlConnection instanceof HttpURLConnection)
        {
            connection = (HttpURLConnection) urlConnection;
        }
        else
        {
            return null;
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String urlString = "";
        String current;
        while((current = in.readLine()) != null)
        {
            urlString += current;
        }
        System.out.println(urlString);
        JSONObject jsonObject = JSONObject.parseObject(urlString);
        return jsonObject;
    }
}
