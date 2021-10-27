# Docker image for javaWeb application
# VERSION 0.0.1
# Author: Kannokaze

### 基础镜像，使用tomcat,javaWeb在Tomcat才能运行
FROM hub.c.163.com/library/tomcat

#作者
MAINTAINER Kannokaze <1131429439@qq.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

#暴露8080端口
EXPOSE 8080

#部署war包
COPY /out/artifacts/gpms_war/gpms_war.war  /usr/local/tomcat/webapps/gpms.war