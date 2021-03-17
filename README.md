# short-link-server

短链接生成服务器

## 项目功能

本项目为web项目，使用了[mine-spring](https://github.com/dzzhyk/mine-spring)构建，将长链接转换为短链接

## 主要内容

1. 使用Murmurhash生成hash值，将hash值转换为62进制数字进一步压缩链接长度
2. 为短链接访问提供302重定向到真实链接的功能

## 项目演示

在线演示网址：[http://s.yankaizhang.com](http://s.yankaizhang.com)

![图片1](imgs/1.png)