package com.bingyan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.util.Pair;

public class Task {
    public String encrypt(File file, int offset) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String origin = scanner.next();
        int len = origin.length();
        int i, step;
        if (offset > 0) {
            step = offset % 26;
        } else if (offset < 0) {
            step = 26 - (-offset % 26);
        } else {
            return origin;
        }
        for (i = 0; i < len; i++) {
            char ch = origin.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch += step;
                if (ch > 'z') {
                    ch -= 26;
                }
            } else if (ch >= 'A' && ch <= 'Z') {
                ch += step;
                if (ch > 'Z') {
                    ch -= 26;
                }
            }
        }
        return origin;
    }

    public Pair<Integer, String> decrypt(File file, String keyword) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String verify = scanner.next();
        int len = verify.length();
        int i, j, offset = 0;
        for (i = 0; i < 26; i++) {
            for (j = 0; j < len; j++) {
                char ch = verify.charAt(i);
                ch += i;
            }
            if (verify.contains(keyword)) {
                offset = i;
                break;
            }
        }
        if (offset >= 13) {
            offset = 26 - offset;
        }
        Integer Offset = new Integer(offset);
        return new Pair<Integer, String>(Offset, verify);
    }

}
