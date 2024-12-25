# 1. BUILD STAGE
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# 2. RUNTIME STAGE
FROM amazoncorretto:17

ARG PROFILE=dev
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/restaurant-service-*.jar /app/

EXPOSE 8888

#ENV DB_URL=jdbc:postgresql://postgres:5432/restaurant_db
#ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}
#ENV EMAIL_HOSTNAME=
#ENV EMAIL_USERNAME=
#ENV EMAIL_PASSWORD=

#CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} restaurant-service-${JAR_VERSION}.jar

CMD java -jar azure-demo-${JAR_VERSION}.jar

# docker build -t rupesh1997/restaurant-service:1.0.0 -f Dockerfile .