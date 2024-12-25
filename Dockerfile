# 1. BUILD STAGE
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# 2. RUNTIME STAGE
FROM amazoncorretto:17

ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/azure-demo-*.jar /app/

EXPOSE 8888
ENV JAR_VERSION=${APP_VERSION}
CMD java -jar azure-demo-${JAR_VERSION}.jar