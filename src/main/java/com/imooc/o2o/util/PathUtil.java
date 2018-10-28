package com.imooc.o2o.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PathUtil {

    private static String separator = System.getProperty("file.separator");

    public static String getImageBasePath() {
        String basePath;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            basePath = "F:/project/image/";
        } else {
            basePath = "/home/xiangzai/image/";
        }
        basePath = basePath.replace("/", separator);
        System.out.println(separator);
        return basePath;
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);

    }

    public static void main(String[] args) {
        System.out.println(getImageBasePath());
    }
}
