package com.longshao.Test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    源码解析arrayList
        可变容量的数组
*/
public class MyArrayList {
    public static void main(String[] args) {
       /* ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        System.out.println(list);*/
      /*  HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "qlh");
        hashMap.put("name", "qqq");
        for (Map.Entry<String, String> m : hashMap.entrySet()) {
            String key = m.getKey();
            String value = m.getValue();
            System.out.println(key + value);
        }
        for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {

        }

        for (Object m : hashMap.values()) {
            System.out.println(m);
        }*/
        int[] A = {1, 5, 8, 0, 3, 4, 8, 7};
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
        }
        System.out.println();
        int[] sort = sort(A);
        for (int a = 0; a < sort.length; a++) {
            System.out.print(sort[a]);
        }
        System.out.println();
    }

    public static int[] sort(int[] a) {
        int temp;
        int[] b = null;
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            b = a;
        }
        return b;

    }

}
