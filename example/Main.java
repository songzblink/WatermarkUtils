
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Create By songzb on 2020/9/25
 */
public class Main {
    public static void main(String[] args) throws IOException {
        WatermarkUtils watermarkUtils = new WatermarkUtils();

        // 加载路径
        String path = "images/";
        File[] files = new File(path).listFiles();

        // 输出路径目录
        String savePath = "images/output/";
        File watermarkFile = new File("images/watermark.png");

        for (File file : files) {
            // file 不为目录
            if (!file.isDirectory()) {
                // 添加文字水印
                // BufferedImage bufferedImage = watermarkUtils.addWatermark(file, "123", 0, 10, new Font("宋体", Font.BOLD, 20), Color.ORANGE);
                // 添加图片水印
                BufferedImage bufferedImage = watermarkUtils.addWatermark(file, watermarkFile, 0, 0, 1);
                // 输出图片
                watermarkUtils.generateWaterFile(bufferedImage, savePath, file.getName());
            }
        }

    }
}
