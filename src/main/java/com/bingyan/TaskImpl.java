package com.bingyan;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;

public class TaskImpl extends Task {
    @Override
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
        char[] originCharArray = originString.toCharArray(), result = new char[size];
        for (int i = 0; i < size; ++i) {
            if (originCharArray[i] >= 'a' && originCharArray[i] <= 'z')
                result[i] = (char) (((originCharArray[i] - 'a') + offset + 26) % 26 + 'a');
            if (originCharArray[i] >= 'A' && originCharArray[i] <= 'Z')
                result[i] = (char) (((originCharArray[i] - 'A') + offset + 26) % 26 + 'A');
        }
        return new String(result);
    }

    @Override
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
        System.out.println(encryptedString);
        System.out.println(keyword);
        char[] encryptedCharArray = encryptedString.toCharArray(), keywordCharArray = keyword.toCharArray();
        int len = keyword.length();
        int[] differenceEncrypted = new int[size], differenceKeyword = new int[len];
        for (int i = 1; i < size; ++i)
            differenceEncrypted[i] = encryptedCharArray[i] - encryptedCharArray[i - 1];
        for (int i = 1; i < len; ++i)
            differenceKeyword[i] = keywordCharArray[i] - keywordCharArray[i - 1];
        System.out.println(Arrays.toString(differenceKeyword));
        int position = size;
        for (int i = 0; i <= size - len; ++i) {
            boolean findKeywordPosition = true;
            for (int j = 1; j < len; j++)
                if (differenceKeyword[j] != differenceEncrypted[i + j]) {
                    findKeywordPosition = false;
                    break;
                }
            if (findKeywordPosition) {
                position = i;
                break;
            }
        }
        int offset = (encryptedCharArray[position] - keywordCharArray[position]) % 26;
        if (offset > 13)
            offset -= 26;
        if (offset < -13)
            offset += 26;
        System.out.println(offset);
        System.out.println(position);
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
