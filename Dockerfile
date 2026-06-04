# Stage 1: Build aplikasi
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Jalankan aplikasi
FROM openjdk:25-jdk-slim
COPY --from=build target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]