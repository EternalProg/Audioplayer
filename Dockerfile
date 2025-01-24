FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN apt-get update && apt-get install -y dos2unix
# Конвертуємо файл mvnw у Unix-формат
RUN dos2unix mvnw

# Завантажуємо залежності Maven у офлайн-режимі
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]