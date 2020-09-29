

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Create By songzb on 2020/9/25
 *
 * @author songzb
 */

public class WatermarkUtils {

    /**
     * 构造一张叠加了图片水印的图片
     *
     * @param bImg     原文件（待被添加水印的图片）的 BufferedImage 带缓冲区图像类
     * @param waterImg 水印文件（图片）的 BufferedImage 带缓冲区图像类
     * @param x        添加水印位置的坐标 x
     * @param y        添加水印位置的坐标 y
     * @param alpha    透明度，值： 完全透明 0.0 ~ 完全不透明 1.0
     * @return 返回一个叠加了图片水印的 BufferedImage 带缓冲区图像类
     * @throws IOException
     */
    public BufferedImage addWatermark(BufferedImage bImg, BufferedImage waterImg, int x, int y, float alpha) throws IOException {

        // if (x > bImg.getWidth() + waterImg.getWidth() || y > bImg.getHeight()) {
        //     throw new RuntimeException("输入坐标非法，请检查是否在合理范围之内！the value of 'x' or 'y' is illegal, check it and make sure they are effective!");
        // }
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("输入透明度alpha非法，请检查是否在合理范围之内！the value of 'alpha' is illegal, check it and make sure it is effective!");
        }

        // 获得水印图
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

        if (waterImgWidth > bImg.getWidth() || waterImgHeight > bImg.getHeight()) {
            throw new RuntimeException("水印图片的尺寸应该小于原图片！the size of watermark image should be smaller than source image!");
        }


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
     * 把图片水印平铺到原图片上
     * @param file  原文件（待被添加水印的图片）
     * @param watermarkFile 水印文件（图片）
     * @param interval  平铺的间隔
     * @param alpha   透明度，值： 完全透明 0.0 ~ 完全不透明 1.0
     * @return  返回一个叠加了图片水印的 BufferedImage 带缓冲区图像类
     * @throws IOException
     */
    public BufferedImage overspreadWatermark(File file, File watermarkFile, int interval, float alpha) throws IOException {
        BufferedImage bImg = ImageIO.read(file);
        int bImgWidth = bImg.getWidth();
        int bImgHeight = bImg.getHeight();

        BufferedImage waterImg = ImageIO.read(watermarkFile);
        int waterImgWidth = waterImg.getWidth();
        int waterImgHeight = waterImg.getHeight();

        if (waterImgWidth > bImgWidth || waterImgHeight > bImgHeight) {
            throw new RuntimeException("水印图片的尺寸应该小于原图片！the size of watermark image should be smaller than source image!");
        }

        for (int x = 0; x < bImgWidth + waterImgWidth; x = x + waterImgWidth + interval) {
            for (int y = 0; y < bImgHeight + waterImgHeight; y = y + waterImgHeight + interval) {
                addWatermark(bImg, waterImg, x, y, alpha);
            }
        }

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
        // 输出
        ImageIO.write(bImage, fileName.split("\\.")[1], out);
    }

}
