package main;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * Create By songzb on 2020/9/25
 */
public class Main {
    public static void main(String[] args) throws IOException {
        AddWatermark addWatermark = new AddWatermark();

        // 加载配置文件
        InputStream inputStream = new BufferedInputStream(new FileInputStream("watermark.properties"));
        //解决读取properties文件中产生中文乱码的问题
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        // 加载配置
        Properties p = new Properties();
        try {
            p.load(bf);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // 加载路径
        String path = "images/";
        File[] files = new File(path).listFiles();

        for (File file : files) {
            if (!file.isDirectory()) {
                // addWatermark.addTextWatermark(file, "测试水印", new Font("宋体", Font.BOLD, 20), Color.white, 4);
                addWatermark.addTextWatermark(file, p.getProperty("context"), new Font(p.getProperty("fontName"),
                                Integer.valueOf(p.getProperty("fontStyle")), Integer.valueOf(p.getProperty("fontsize"))),
                        Color.getColor(p.getProperty("color")), Integer.valueOf(p.getProperty("location")));
            }
        }

    }
}
