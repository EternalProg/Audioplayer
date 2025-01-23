#!/usr/bin/env sh

# Оновлюємо пакети (без sudo)
echo "Оновлення списку пакетів..."
apt-get update -y || { echo "Помилка оновлення пакетів. Вийшов."; exit 1; }

# Встановлюємо OpenJDK 17
echo "Встановлення OpenJDK 17..."
apt-get install -y openjdk-17-jdk || { echo "Помилка встановлення OpenJDK 17."; exit 1; }

# Перевірка Java
echo "Перевірка версії Java..."
java -version || { echo "Java не встановлено. Вийшов з помилкою."; exit 1; }

# Встановлюємо Maven
echo "Встановлення Maven..."
apt-get install -y maven || { echo "Помилка встановлення Maven."; exit 1; }

# Перевірка Maven
echo "Перевірка версії Maven..."
mvn -version || { echo "Maven не встановлено. Вийшов з помилкою."; exit 1; }

echo "Усі залежності успішно встановлені!"

mvn package