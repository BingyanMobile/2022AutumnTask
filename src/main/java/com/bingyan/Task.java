package com.bingyan;

import javafx.util.Pair;

import java.io.File;

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
        return null;
    }

    /**
     * 将指定的文件内容进行暴力破解
     * 已知正确破解后内容中包含用户指定的字符串
     * 返回一个Map.Entry<Integer,String>对象
     * Integer为正确破解的偏移量，负数代表左移，正数代表右移
     * String为正确解密的内容。
     *
     * @param file    指定的文件
     * @param keyword 正确破解后包含的字符串
     * @return Map.Entry<正确破解的偏移量, 正确破解后的字符串>
     */
    public Pair<Integer, String> decrypt(File file, String keyword) {
        return new Pair<>(0, null);
    }

}
