# 1. Aşama: Build aşaması
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# 2. Aşama: Sadece jar dosyasını çalıştıran aşama
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
