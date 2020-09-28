# Auto-Watermark

当前版本能够通过配置 `watermark.properties` 文件来设置水印自动添加，只需要按照指定的目录格式存放待添加水印图片，命令行复制如下命令 `java -jar .\AddWatermark.jar` ，即可生成带水印的目标图片。源代码在 `src/` 目录下，下一版本打算添加图片水印功能。

代码的思路我写到了[博客](https://zbsong.top/article/%E6%89%B9%E9%87%8F%E7%BB%99%E5%9B%BE%E7%89%87%E6%B7%BB%E5%8A%A0%E6%B0%B4%E5%8D%B0/)里面


# 使用方式

按照目录结构，将需要的添加水印的图片放入 `images/` 文件夹后，按住 `SHIFT` + `鼠标右键` 打开 `PowerShell` ，输入命令 `java -jar .\AddWatermark.jar` ，就能够在目录 `iamges/output` 下找到带有水印的图片。

# 目录结构

|- 	watermark.properties  需要手动配置水印的内容、字体类型、字体样式、字体大小、字体颜色以及字体的位置

|- 	AddWatermark.jar 打包好的程序代码

|- 	images 将所有需要添加水印的图片放入该文件夹中

​		|- 	output 该文件夹自动生成，存放生成的带水印图片

