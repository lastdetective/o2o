package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class ImageUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ImageUtil.class);
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFomrmat = new SimpleDateFormat("yyyyMMddHHmmss");


    private static final Random r = new Random();

    public static void main(String[] args) throws IOException {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(basePath);
        Thumbnails.of(new File("D://th.jpg"))
                .size(1920, 1080).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/th2.jpg")), 0.25f)
                .outputQuality(1f).toFile("D://thnew.jpg");
    }

    /**
     * 生成缩略图
     *
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder imageHolder, String targetAddr) {

        String realFileName = getRandomFileName();
        String extension = getFileExtention(imageHolder.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImageBasePath() + relativeAddr);
        System.out.println("这是basePath" + basePath);
        try {
            Thumbnails.of(imageHolder.getImage()).size(1920, 1080)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "th2.jpg")), 0.2f)
                    .outputQuality(1f).toFile(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * @param imageHolder 传入的文件名和流
     * @param targetAddr  该店铺的图片文件夹的相对路径
     */
    public static String generateNormalImg(ImageHolder imageHolder, String targetAddr) {
        // 创建
        makeDirPath(targetAddr);
        // 获取存储到数据库时的随机文件名
        String storedRandomFilename = getRandomFileName();
        // 获取文件扩展名
        String extension = getFileExtention(imageHolder.getImageName());
        // 获取文件存储的相对路径
        String relativeAddr = targetAddr + storedRandomFilename + extension;
        log.info("文件的相对路径是" + relativeAddr);

        // 获取文件存储的绝对路径
        String absolutePath = PathUtil.getImageBasePath() + relativeAddr;
        log.info("文件的绝对（存储）路径是" + absolutePath);

        try {
            Thumbnails.of(imageHolder.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "th2.jpg")), 0.2f)
                    .outputQuality(0.9f).toFile(absolutePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relativeAddr;

    }

    /**
     * 创建目标路径所涉及到的目录
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 扩展名
     * 获取文件
     *
     * @param thumbnail
     * @return
     */
    private static String getFileExtention(String thumbnail) {
        return thumbnail.substring(thumbnail.lastIndexOf("."));
    }

    /**
     * 生成随机文件
     *
     * @return
     */
    public static String getRandomFileName() {
        int ranNum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFomrmat.format(new Date());
        return ranNum + nowTimeStr;

    }

    /**
     * 删除文件或文件目录下的所有文件
     *
     * @param storePath
     */

    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImageBasePath() + storePath);
        if (fileOrPath.exists()) {
            // 如果是目录
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        file.delete();

                    }
                }
            }
            // 如果是文件 就把文件删除 如果是目录 先删除目录下的文件 再删除目录
            fileOrPath.delete();
        }


    }


}
