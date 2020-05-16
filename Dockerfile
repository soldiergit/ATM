# docker容器会下拉一个最新的基础镜像（tomcat）用于运行本项目，8080是该镜像内部运行端口
#基础镜像
FROM tomcat
#Message(维修人员)
MAINTAINER soldier<soldier_wyyx@163.com.com>
#构建日期
ENV REFRESHED_AT 2020-04-29
#切换镜像目录，进入/usr目录
WORKDIR /usr/local/tomcat/webapps
#复制编译后的文件
COPY target/atm.war  /usr/local/tomcat/webapps/atm.war
#公开端口
EXPOSE 8080
#设置启动命令
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]