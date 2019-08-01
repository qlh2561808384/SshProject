package com.longshao.Test;

import ij.plugin.DICOM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DicomToJpg {
    /**
     * 根据dicom文件生成jpg图片 （项目dicom路径）
     * <p/>
     * 这里输入的是img文件夹的dicom文件名字，
     * 运行即可得到一个jpg图片，显示的是dicom里面的图形
     */
    public static void localConverse(String projectPath, String fillPath) {
//        String projectPath = System.getProperty("user.dir");
        DICOM dicom = new DICOM();
        String imagePath = projectPath + "img\\" + fillPath;
        dicom.run(imagePath);
        BufferedImage bi = (BufferedImage) dicom.getImage();
        imagePath = projectPath + "img\\" + fillPath + ".jpg";
        try {
            ImageIO.write(bi, "jpg", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输入一个dicom文件的绝对路径和名字(既是盘符路径)
     * 获取一个jpg文件
     */
    public static void diskConverse(String fillPath) {
        try {
            DICOM dicom = new DICOM();
            dicom.run(fillPath);
            BufferedImage bi = (BufferedImage) dicom.getImage();
            int width = bi.getWidth();
            int height = dicom.getHeight();
            System.out.println("width: " + width + "\n" + "height: " + height);
            String imagePath = fillPath + ".jpg";
            ImageIO.write(bi, ".jpg", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DicomToJpg.diskConverse("E:\\test\\201907010932040012ABD.DCM");
    }
}
