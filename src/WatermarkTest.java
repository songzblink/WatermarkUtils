import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Create By songzb on 2020/9/29
 */
public class WatermarkTest {

    private WatermarkUtils watermarkUtils = new WatermarkUtils();

    /**
     * 测试图片水印铺满图片
     */
    @Test
    public void test1() throws IOException {
        BufferedImage bufferedImage = watermarkUtils.overspreadWatermark(new File("images/test.png"), new File("images/watermark.png"), 70, (float) 0.5);
        watermarkUtils.generateWaterFile(bufferedImage, "images/output/", "test.png");
    }

}
