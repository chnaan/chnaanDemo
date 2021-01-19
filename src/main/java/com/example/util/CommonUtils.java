package com.example.util;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2021/01/11 14:17
 */
public class CommonUtils {

    public static long statisticsString(File file,String targetStr) {
        BufferedReader reader = null;
        List<String> strings = new ArrayList<>();
        try {

            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                strings.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        String tempStr = String.join("", strings);
        int count = 0 ;
        int targetStrLen = targetStr.length();
        for (int i =0 ;i<tempStr.length()-targetStrLen;i++){
            if (!tempStr.substring(i,i+targetStrLen).equals(targetStr)){
                continue;
            }
            count++;
        }
        return count;


    }

    public static void copyImg(String srcFilePath,String dstFileName,String dstFilePath) throws IOException {
        String imgStr = img2code(srcFilePath);
        byte[] bytes = Base64.decodeBase64(imgStr);

        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }

        File file = new File(dstFilePath+dstFileName);
        if (!file.exists()&&file.createNewFile()){
            System.out.println("文件不存在，已重新创建！");;
        }
        OutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.flush();
        out.close();
    }

    private static String img2code(String filePath) {
        InputStream inputStream;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
    }
}
