package com.example.service;

import com.example.util.CommonUtils;

import java.io.File;
import java.io.IOException;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/15 10:20
 */
public class FileService {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\python项目\\jd_seckill.log");
        System.out.println(CommonUtils.statisticsString(file,"90016"));
    }
}