FROM gradle:jdk21-jammy AS build
COPY . .

RUN gradle build

FROM openjdk:21-ea-jdk
VOLUME /tmp
COPY --from=build home/gradle/build/libs/product.jar app.jar
ENV ACTIVE_PROFILES=http,mongodb

ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=${ACTIVE_PROFILES}"]