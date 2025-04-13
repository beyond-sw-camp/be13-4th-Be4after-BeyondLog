FROM eclipse-temurin:21-jre-alpine
LABEL maintainer="godbyul <skypower4203@gmail.com>"
LABEL version="1.0"
COPY ./be-4th-4team-backend/build/libs/be-4th-4team-0.0.1-SNAPSHOT.jar /root
ARG BUILD_PORT=8088
ENV TZ=Asia/Seoul
EXPOSE ${BUILD_PORT}
WORKDIR /root
CMD ["java", "-jar", "be-4th-4team-0.0.1-SNAPSHOT.jar"]
