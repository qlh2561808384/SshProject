package com.longshao.Test;

import java.util.Arrays;

public class Cheshi {
    public static void main(String[] args) {
        String src = "  we go to  school";
//        String src = "Hello Java,Hello China.";//需要处理的字符串
        String rever = rever(src);
        System.out.print(src.length());
        System.out.println(rever.length());
        System.out.println(rever);
    }

    /*public static String rever(String src) {
        StringBuilder tmp = new StringBuilder(20);//定义一个StringBuilder对象
        StringBuilder goal = new StringBuilder(src.length());//定义一个StringBuilder对象来存放最终要输出的信息
        char c;//定义一个字符变量
        for (int i=src.length()-1;i>=0;i--){
            c = src.charAt(i);//从后往前取字符
            if(c ==' ' || c == ',' || c == '.'){//判断是否为分隔字符
                goal.append(tmp);//如果是的话就把tmp加入到goal中来
                goal.append(c);//在把分隔字符也一起加入
                tmp.delete(0,tmp.length());//清空tmp
            }else {
                tmp.insert(0,c);//如果不是分隔字符，说明单词未完整，继续加入tmp中
            }
        }
        if (!tmp.equals("")){
            goal.append(tmp);//如果tmp中还有内容，在添加到goal中
        }
        System.out.println(goal.toString());//输出
        return goal.toString();
    }*/
    public static String rever(String str) {
        StringBuilder tempStr = new StringBuilder();//临时存储字符串
        StringBuilder goalStr = new StringBuilder();//最终存储字符串
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            //处理特殊字符串 如果有其他特殊字符 可以家在if条件里面
            if (c == ' ' || c == ',' || c == '.') {
                goalStr.append(tempStr);//将临时存储的字符串放进目标存储的字符串中
                goalStr.append(c);//将特殊字符放进目标字符串中
                tempStr.delete(0, tempStr.length());//清空临时字符串 待下次存储
            } else {
                tempStr.insert(0, c);//将非特殊字符放进临时字符串中 等单词拼接完成 一起放进目标字符串中
            }
        }
        //防止最前面没有特殊字符 最前面的一个单词放不进去
        if (!tempStr.equals("")) {
            goalStr.append(tempStr);
        }
        return goalStr.toString();
    }
/*    public static String rever(String str) {
        //这样写有一个问题就是 如果字符串最后面有空格split()方法是不能把最后的空格分出来的。 字符串前面有空格没关系
        StringBuilder sb = new StringBuilder();//用于接收莫表字符串
        String[] strings = str.split(" ");//按照空格split
        //遍历数组
        for (int i = strings.length - 1; i >= 0; i--) {
            if (i != 0) {
                sb.append(strings[i] + " ");
            }else {
                sb.append(strings[i]);
            }
        }
        return sb.toString();
    }*/
}
