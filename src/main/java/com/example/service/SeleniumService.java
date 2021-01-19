package com.example.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

/**
 *测试selenium
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/23 10:41
 */
@Service
public class SeleniumService {
    public void test() throws InterruptedException {
        String user = "18645652";
        String password = "ljblx@970618";

        //设置本地chromeDriver地址
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        //创建Chrome无头参数
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");

        WebDriver driver = new ChromeDriver();
        driver.get("https://oa.transsion.com/");

        Thread.sleep(2000);
        System.out.println("开始登陆");
        driver.findElement(By.id("loginid")).sendKeys(user);
        driver.findElement(By.id("userpassword")).sendKeys(password);

        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"null\"]/div")).click();

        Thread.sleep(2000);
        driver.findElements(By.tagName("table")).get(4).findElements(By.tagName("td")).forEach(s-> System.out.println(s.getText()));

    }
}
