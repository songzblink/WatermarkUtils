package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Create By songzb on 2020/9/25
 */

public class WatermarkUtils {

    /**
     * 构造一张叠加了图片水印的图片
     *
     * @param file          原文件（待被添加水印的图片）
     * @param watermarkFile 水印文件（图片）
     * @param x             添加水印位置的坐标 x
     * @param y             添加水印位置的坐标 y
     * @param alpha         透明度，值： 完全透明 0.0 ~ 完全不透明 1.0
     * @return 返回一个叠加了图片水印的 BufferedImage 带缓冲区图像类
     */
    public BufferedImage addWatermark(File file, File watermarkFile, int x, int y, float alpha) throws IOException {

        // 获得底图
        BufferedImage bImg = ImageIO.read(file);

        if (x > bImg.getWidth() || y > bImg.getHeight()) {
            throw new RuntimeException("输入坐标非法，请检查是否在合理范围之内！the value of 'x' or 'y' is illegal, check it and make sure they are effective!");
        }
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("输入透明度alpha非法，请检查是否在合理范围之内！the value of 'alpha' is illegal, check it and make sure it is effective!");
        }

        // 获得水印图
        BufferedImage waterImg = ImageIO.read(watermarkFile);
        int waterImgWidth = waterImg.getWidth();
        int waterImgHeight = waterImg.getHeight();

        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D graphics = bImg.createGraphics();

        // 在图形和图像中设置混合和透明效果
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        graphics.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        graphics.dispose();

        return bImg;
    }

    /**
     * 构造一张叠加了文字水印的图片
     *
     * @param file            原文件（待被添加水印的图片）
     * @param watermarkString 要被添加的文字水印
     * @param x               添加水印位置的坐标 x
     * @param y               添加水印位置的坐标 y
     * @param font            文字水印的字体
     * @param color           文字水印的颜色
     * @return 返回一个叠加了文字水印的 BufferedImage 带缓冲区图像类
     * @throws IOException
     */
    public BufferedImage addWatermark(File file, String watermarkString, int x, int y, Font font, Color color) throws IOException {
        // 获得底图
        BufferedImage bImg = ImageIO.read(file);

        if (x > bImg.getWidth() || y > bImg.getHeight()) {
            throw new RuntimeException("输入坐标非法，请检查是否在合理范围之内！the value of 'x' or 'y' is illegal, check it and make sure they are effective!");
        }

        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D graphics = bImg.createGraphics();
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(watermarkString, x, y);
        return bImg;
    }

    /**
     * 输出水印图片到指定目录中
     *
     * @param bImage   添加水印后的 BufferedImage 带缓冲区图像类
     * @param savePath 输出目录的路径
     * @param fileName 原文件（图片）的名称
     */

    public void generateWaterFile(BufferedImage bImage, String savePath, String fileName) throws IOException {
        File outputDir = new File(savePath);
        FileOutputStream out = null;

        // 输出目录不存在
        if (!outputDir.exists()) {
            // 创建输出目录
            outputDir.mkdirs();
        }
        // 输出目录存在
        if (outputDir.exists()) {

            out = new FileOutputStream(outputDir + "/watermarked_" + fileName);
        }

        ImageIO.write(bImage, fileName.split("\\.")[1], out);
    }

}
