package com.longshao.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Max {
    public static String[] stringFilter(String str) {
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        return p.split(str);
    }

    private static void getAllLcs( String a, String b, int[][] mux, int i, int j, String path, Set<String> paths) {

        StringBuilder pathBuilder = new StringBuilder(path);
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                pathBuilder.append(a.charAt(i - 1));
                --i;
                --j;
            }else {
                if (mux[i - 1][j] > mux[i][j - 1]) {
                    --i;
                } else if (mux[i - 1][j] < mux[i][j - 1]) {
                    --j;
                } else {
                    getAllLcs(a, b, mux, i - 1, j, pathBuilder.toString(), paths);
                    getAllLcs(a, b, mux, i, j - 1, pathBuilder.toString(), paths);
                    return;
                }
            }
        }
        paths.add(pathBuilder.toString());
    }

    public static String findLCS(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        // 要返回的结果
        StringBuilder result = new StringBuilder();

        // 将字符串反转
        String reverse = new StringBuilder(input).reverse().toString();

        // 字符串长度
        int len = input.length();

        // 矩阵 -> 二维数组
        int[][] tmp = new int[len + 1][len + 1];
        //根据字符相等写出相应矩阵的值
        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < len + 1; j++) {
                if (input.charAt(i - 1) == reverse.charAt(j - 1)) {
                    tmp[i][j] = tmp[i - 1][j - 1] + 1;
                } else {
                    tmp[i][j] = Math.max(tmp[i - 1][j], tmp[i][j - 1]);
                }
            }
        }

        Set<String> paths = new HashSet<String>(){};
        Max.getAllLcs(input, reverse, tmp, input.length(), reverse.length(), "", paths);

        return String.join("/", paths);
    }

    public static String maxs(String input) {
        String[] prepare = stringFilter(input);
        StringBuffer sb = new StringBuffer();
        for (String str : prepare) {
            String result = findLCS(str);
            sb.append(result);
            sb.append("/");
        }
        return sb.substring(0, sb.length()-1);
    }

    public static void main(String[] args) {
        // TODO 输出最长对称字符串：goog
        String input1 = "google";
        System.out.println(maxs(input1));
        // TODO 输出3个最长对称字符串：aba/aca/ada
        String input2 = "abcda";
        System.out.println(maxs(input2));
        // TODO 输出2个最长对称字符串：pop/upu，不会输出特殊字符的对称字符串p-p
        String input3 = "pop-upu";
        System.out.println(maxs(input3));
    }
}
