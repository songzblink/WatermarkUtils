# Auto-Watermark

当前版本能够通过配置 `watermark.properties` 文件来设置水印自动添加，只需要按照指定的目录格式存放待添加水印图片，命令行复制如下命令 `java -jar .\AddWatermark.jar` ，即可生成带水印的目标图片。下一版本打算添加图片水印功能。

# 使用方式

按照目录结构，将需要的添加水印的图片放入 `images/` 文件夹后，按住 `SHIFT` + `鼠标右键` 打开 `PowerShell` ，输入命令 `java -jar .\AddWatermark.jar` ，就能够在目录 `iamges/output` 下找到带有水印的图片。

# 目录结构

|- 	watermark.properties  需要手动配置水印的内容、字体类型、字体样式、字体大小、字体颜色以及字体的位置

|- 	AddWatermark.jar 打包好的程序代码

|- 	images 将所有需要添加水印的图片放入该文件夹中

​		|- 	output 该文件夹自动生成，存放生成的带水印图片

# watermark.properties 配置详解

```properties
context=测试水印
fontName=宋体
# PLAIN-1   BOLD-2    ITALIC-3  ROMAN_BASELINE CENTER_BASELINE HANGING_BASELINE TRUETYPE_FONT TYPE1_FONT
fontStyle=1
fontsize=20
color=white
# 1~9
location=8
```

- context：指定水印的内容
- fontName：指定水印的字体
- fontStyle：指定字体的样式
  - PLAIN：普通字体，值为1
  - BOLD：粗体，值为2
  - ITALIC：斜体，值为3
- fontsize：指定字体大小，单位是像素
- color：指定字体的颜色，常见的英文颜色单词都能使用
- location：指定水印的添加位置，一共提供了9个可选位置，分别是上左、上中、上右、中左、正中、中右、下左、下中、下右，对应值为1~9
