package com.example.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/23 10:41
 */
@Service
public class SeleniumService {
    public void test(){
        //设置本地chromeDriver地址
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        //创建无Chrome无头参数
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://www.baidu.com");

        String title = driver.getTitle();
        System.out.printf(title);

    }
}
