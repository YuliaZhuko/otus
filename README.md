# Homework 3 – REST Assured API Testing

## 📌 Описание

Этот проект представляет собой реализацию третьего домашнего задания курса OTUS, посвящённого тестированию REST API с использованием библиотеки Rest-Assured. В рамках задания реализованы следующие компоненты:

DTO-классы: PetDTO, CategoryDTO, TagDTO – используются для сериализации и десериализации JSON-объектов при взаимодействии с API.

Тесты: PetApiTest – содержит набор тестов для проверки различных сценариев работы с API.


## 🛠 Стек технологий

- Java 21
- Maven
- REST Assured
- JUnit 5
- Jackson
- Lombok
- Checkstyle

## 📂 Структура проекта
otus/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── dto/
│   │           ├── PetDTO.java
│   │           ├── CategoryDTO.java
│   │           └── TagDTO.java
│   └── test/
│       └── java/
│           └── tests/
│               └── PetApiTest.java
├── pom.xml
├── .gitignore
└── spotbugs-exclude.xml

## Запуск тестов
1. Убедитесь, что у вас установлен JDK 21 и Maven.
2.  Склонируйте репозиторий и перейдите в директорию проекта:
    bash
    Копировать
    Редактировать
    git clone https://github.com/YuliaZhuko/otus.git
    cd otus
    git checkout homework_3

3. Запустите тесты с помощью Maven:
   bash
   Копировать
   Редактировать
   mvn clean test
