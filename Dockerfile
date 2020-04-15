#基础镜像
FROM tomcat
#Message(维修人员)
MAINTAINER soldier<soldier_wyyx@163.com.com>
#构建日期
ENV REFRESHED_AT 2020-04-14
#切换镜像目录，进入/usr目录
WORKDIR /usr/local/tomcat/webapps
#复制编译后的文件
COPY target/ATM.war  /usr/local/tomcat/webapps/ATM.war
#公开端口
EXPOSE 8080
#设置启动命令
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]