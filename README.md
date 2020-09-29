# WatermarkUtils

Java程序包，支持添加文字水印和图片水印。导入jar包`WatermarkUtils.jar` 即可使用。

## API

- **addWatermark**(File file, File watermarkFile, int x, int y, float alpha); 添加图片水印
- **addWatermark**(BufferedImage bImg, BufferedImage waterImg, int x, int y, float alpha); 添加图片水印
- **overspreadWatermark**(File file, File watermarkFile, int interval, float alpha); 以一定的间隔平铺图片水印
- **addWatermark**(File file, String watermarkString, int x, int y, Font font, Color color); 添加文字水印
- **generateWaterFile**(BufferedImage bImage, String savePath, String fileName); 输出水印图片