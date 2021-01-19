package com.example.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/29 14:40
 */
@Service
public class ImageService {



    public void code2img() throws IOException {

        String imgStr = img2code();

        System.out.println(imgStr);

        byte[] bytes = Base64.decodeBase64(imgStr);

        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }


        OutputStream out = new FileOutputStream("C:\\Users\\pradmin\\Desktop\\test.jpg");
        out.write(bytes);
        out.flush();
        out.close();

    }

    public String img2code(){
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\pradmin\\Desktop\\壁纸.jpg");
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
    }
}
