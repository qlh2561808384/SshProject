package com.longshao.Test;

import org.junit.platform.commons.util.StringUtils;

public class UtilPath {
    public static void main(String[] args) {
        String systemName = System.getProperty("os.name");
//        System.out.println(getProjectPath());
    }

    /**
     * 获取到classes目录
     *
     * @return path
     */
    public static String getClassPath() {
        String systemName = System.getProperty("os.name");

//判断当前环境，如果是Windows 要截取路径的第一个 '/'
        if (!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") != -1) {
            return UtilPath.class.getResource("/").getFile().toString().substring(1);
        } else {
            return UtilPath.class.getResource("/").getFile().toString();
        }
    }
}
