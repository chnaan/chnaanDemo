package com.example.service;

import com.example.util.CommonUtils;
import lombok.NonNull;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/07 15:02
 */
public class UseLessService {
    private static final String CAPTCHA_PATH = "D:\\chnaanTest\\opencv\\CAPTCHA\\";
    private static final String SINGLE_CHAR__PATH = "D:\\chnaanTest\\opencv\\single_char\\";
    public static void main(String[] args) throws IOException {

        String fileName = getImage();
        // 加载动态库
        System.load("D:\\chnaanTest\\opencv\\x64\\opencv_java451.dll");
        Mat image = Imgcodecs.imread(CAPTCHA_PATH+fileName, Imgcodecs.IMREAD_GRAYSCALE);

        Mat target = new Mat();
        Imgproc.threshold(image, target, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);//灰度图像二值化

        Imgproc.adaptiveThreshold(target, target, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 3);
        int width = target.width();
        int height = target.height();
        for (int i=1;i<=4;i++){
            int x = (width/4)*(i-1);
            File file = new File(SINGLE_CHAR__PATH+System.currentTimeMillis()+".jpg");
            if (!file.exists()){
                file.createNewFile();
            }
            Mat tempMat = new Mat(target,new Rect(x,0,width/4,height));
            Imgcodecs.imwrite(file.getAbsolutePath(),tempMat);

        }

    }

    private static String getImage() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://pfgatewayidct.transsion.com:9088/service-pt-unified-auth/pt-unified-auth/VerifyCode?t=1610344233810");

        String srcFilePath = driver.findElement(By.tagName("img")).getScreenshotAs(OutputType.FILE).getAbsolutePath();

        String dstFileName = System.currentTimeMillis() +".jpg";
        CommonUtils.copyImg(srcFilePath, dstFileName,CAPTCHA_PATH);
        System.out.println("获取验证码成功！");
        return dstFileName;
    }
}

