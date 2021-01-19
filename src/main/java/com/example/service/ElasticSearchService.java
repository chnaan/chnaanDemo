package com.example.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/19 12:14
 */
@Service
public class ElasticSearchService {

    @Qualifier("esRestClient")
    @Autowired
    RestHighLevelClient restHighLevelClient;
    public void addTest() throws IOException {

        IndexRequest indexRequest = new IndexRequest("indextest");
        String source ="{\n" +
                "  \"count\": true,\n" +
                "  \"current\": 1,\n" +
                "  \"param\": {\n" +
                "  },\n" +
                "  \"size\": 10\n" +
                "}";
        indexRequest.source(source, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

}
