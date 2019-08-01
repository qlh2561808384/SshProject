package com.longshao.Test;

import org.junit.jupiter.api.Test;

public class arrayList {
    public static void main(String[] args) {
        String hello = rever("hello");
        System.out.println(hello);
    }
    public static String rever(String string){
        StringBuilder sb = new StringBuilder();
        for (int i = string.length()-1; i >= 0; i--) {
            char c = string.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }
}
