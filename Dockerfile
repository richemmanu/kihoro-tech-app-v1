FROM registry.access.redhat.com/ubi9/openjdk-21:latest
# ownership
LABEL maintainer="Kihoro Richard <rkihoro@kcbgroup.com>" version="v1.0.0"
# env variables
ENV TZ=Africa/Nairobi
# App port
EXPOSE 8080
# change user
USER root
COPY target/*.jar /deployments/app.jar
WORKDIR /deployments
# revert user
USER jboss
# CMD
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
