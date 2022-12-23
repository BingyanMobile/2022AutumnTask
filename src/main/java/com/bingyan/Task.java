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
        char[] edit = origin.toCharArray();
        int i, step;
        if (offset > 0) {
            step = offset % 26;
        } else if (offset < 0) {
            step = 26 - (-offset % 26);
        } else {
            return origin;
        }
        for (i = 0; i < len; i++) {
            if (edit[i] >= 'a' && edit[i] <= 'z') {
                edit[i] += step;
                if (edit[i] > 'z') {
                    edit[i] -= 26;
                }
            } else if (edit[i] >= 'A' && edit[i] <= 'Z') {
                edit[i] += step;
                if (edit[i] > 'Z') {
                    edit[i] -= 26;
                }
            }
        }
        origin = String.copyValueOf(edit);
        return origin;
    }

    public Pair<Integer, String> decrypt(File file, String keyword) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String verify = scanner.next();
        char[] edit = verify.toCharArray();
        int len = verify.length();
        int i, j, offset = 0;
        for (i = 1; i <= 26; i++) {
            for (j = 0; j < len; j++) {
                if (edit[j] >= 'a' && edit[j] <= 'z') {
                    edit[j] += 1;
                    if (edit[j] > 'z') {
                        edit[j] -= 26;
                    }
                } else if (edit[j] >= 'A' && edit[j] <= 'Z') {
                    edit[j] += 1;
                    if (edit[j] > 'Z') {
                        edit[j] -= 26;
                    }
                }
            }
            verify = String.copyValueOf(edit);
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
