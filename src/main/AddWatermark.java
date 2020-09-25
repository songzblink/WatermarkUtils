package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Create By songzb on 2020/9/25
 */

public class AddWatermark {
    final static int UPLEFT = 1;
    final static int UPMID = 2;
    final static int UPRIGHT = 3;
    final static int MIDLEFT = 4;
    final static int MIDMID = 5;
    final static int MIDRIGHT = 6;
    final static int DOWNLEFT = 7;
    final static int DOWNMID = 8;
    final static int DOWNRIGHT = 9;

    public void addTextWatermark(File file, String watermark, Font font, Color color, int location) throws IOException {
        Image image = ImageIO.read(file);
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // 将图片加载到内存中
        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bimage.createGraphics();
        g.setFont(font);
        g.setColor(color);
        g.drawImage(image, 0, 0, null);

        int x = 0, y = 0;
        if (location == UPLEFT) {
            // 左上角
            x = 0;
            y = font.getSize();
        } else if (location == UPMID) {
            // 上方中间
            x = width / 2 - (watermark.length() * font.getSize()) / 2;
            y = font.getSize();
        } else if (location == UPRIGHT) {
            // 右上角
            x = width - (watermark.length() + 1) * font.getSize();
            y = font.getSize();
        } else if (location == MIDLEFT) {
            x = 0;
            y = height / 2 + font.getSize();
        } else if (location == MIDMID) {
            x = width / 2 - (watermark.length() * font.getSize()) / 2;
            y = height / 2 - font.getSize() / 2;
        } else if (location == MIDRIGHT) {
            x = width - (watermark.length() + 1) * font.getSize();
            y = height / 2 - font.getSize() / 2;
        } else if (location == DOWNLEFT) {
            x = 0;
            y = height - font.getSize();
        } else if (location == DOWNMID) {
            x = width / 2 - (watermark.length() * font.getSize()) / 2;
            y = height - font.getSize();
        } else if (location == DOWNRIGHT) {
            x = width - (watermark.length() + 1) * font.getSize();
            y = height - font.getSize();
        } else {
            throw new RuntimeException("输入水印位置不合法！");
        }
        g.drawString(watermark, x, y);

        // 输出

        File outputFile = new File(file.getParentFile().getPath() + "/output");
        FileOutputStream out = null;
        if (!outputFile.exists()) {
            // 输出目录不存在
            // 创建输出目录
            outputFile.mkdirs();
        }
        if (file.exists()) {
            // 输出目录存在
            out = new FileOutputStream(outputFile + "/watermark" + file.getName());
        }
        String formatName = file.getName().split("\\.")[1];
        if (out != null) {
            ImageIO.write(bimage, formatName, out);
        }
        g.dispose();
        out.close();
    }

    public  InputStream getInputStream(String ConfigFile) {
        return this.getClass().getClassLoader().getResourceAsStream(ConfigFile);
    }
    public static void main(String[] args) throws IOException {


    }
}
