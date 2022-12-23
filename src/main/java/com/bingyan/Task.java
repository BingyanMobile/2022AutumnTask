package com.bingyan;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Task {
    /**
     * 将指定文件的内容使用凯撒密码，后移用户指定位数
     * 不修改文件，直接返回加密结果
     *
     * @param file   指定的文件
     * @param offset 指定的偏移量
     * @return String 加密结果
     */
    public String encrypt(File file, int offset) {
        offset %= 26;
        String originString = null;
        int size = 0;
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            originString = new String(bytes);
        } catch (IOException e) {
            System.out.print("Exception");
        }
        assert originString != null;
        char[] originCharArray = originString.toCharArray(), result = new char[size];
        for (int i = 0; i < size; ++i) {
            if (originCharArray[i] >= 'a' && originCharArray[i] <= 'z')
                result[i] = (char) (((originCharArray[i] - 'a') + offset + 26) % 26 + 'a');
            if (originCharArray[i] >= 'A' && originCharArray[i] <= 'Z')
                result[i] = (char) (((originCharArray[i] - 'A') + offset + 26) % 26 + 'A');
        }
        return new String(result);
    }

    /**
     * 将指定的文件内容进行暴力破解
     * 已知正确破解后内容中包含用户指定的字符串
     * 返回一个Map.Entry<Integer,String>对象
     * Integer为正确破解的偏移量，负数代表左移，正数代表右移
     * String为正确解密的内容
     *
     * @param file    指定的文件
     * @param keyword 正确破解后包含的字符串
     * @return Map.Entry<正确破解的偏移量, 正确破解后的字符串>
     */
    public Pair<Integer, String> decrypt(File file, String keyword) {
        String encryptedString = null;
        int size = 0;
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            encryptedString = new String(bytes);
        } catch (IOException e) {
            System.out.print("Exception");
        }
//        System.out.println(encryptedString);
//        System.out.println(keyword);
        assert encryptedString != null;
        char[] encryptedCharArray = encryptedString.toCharArray(), keywordCharArray = keyword.toCharArray();
        int len = keyword.length();
        int[] differenceEncrypted = new int[size], differenceKeyword = new int[len];
        for (int i = 1; i < size; ++i)
            differenceEncrypted[i] = encryptedCharArray[i] - encryptedCharArray[i - 1];
        for (int i = 1; i < len; ++i)
            differenceKeyword[i] = keywordCharArray[i] - keywordCharArray[i - 1];
        int position = size;
        for (int i = 0; i <= size - len; ++i) {
            boolean findKeywordPosition = true;
            for (int j = 1; j < len; ++j)
                if ((differenceKeyword[j] - differenceEncrypted[i + j]) % 26 != 0) {
                    findKeywordPosition = false;
                    break;
                }
            if (findKeywordPosition) {
                position = i;
                break;
            }
        }
        int offset = (encryptedCharArray[position] - keywordCharArray[0]) % 26;
        if (offset > 13)
            offset -= 26;
        if (offset < -13)
            offset += 26;
//        System.out.println(offset);
//        System.out.println(position);
        char[] result = new char[size];
        for (int i = 0; i < size; ++i) {
            if (encryptedCharArray[i] >= 'a' && encryptedCharArray[i] <= 'z')
                result[i] = (char) (((encryptedCharArray[i] - 'a') - offset + 26) % 26 + 'a');
            if (encryptedCharArray[i] >= 'A' && encryptedCharArray[i] <= 'Z')
                result[i] = (char) (((encryptedCharArray[i] - 'A') - offset + 26) % 26 + 'A');
        }
        return new Pair<>(offset, String.valueOf(result));
    }

}
