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
public class Prototype {
    public static void main(String[] args) throws IOException {

        // 1、加载图片
        File picture = new File("images/test.png");
        Image image = ImageIO.read(picture);

        // 2、修改图片
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();

        Font font = new Font("宋体",Font.BOLD, 10);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null);
        g.drawString("水印测试", 0, font.getSize());

        // 3、输出图片
        FileOutputStream out = new FileOutputStream("images/output.png");
        ImageIO.write(bimage,"png",out);
        g.dispose();
        out.close();
    }
}
