# Otus Homework 6

Учебный проект для курса OTUS. Автоматизация тестирования на Java с использованием Playwright.

## Стек технологий
- Java 17+
- Playwright
- JUnit 5
- Maven
- Allure (если используется)
- Guice (для внедрения зависимостей)

## Структура проекта
src
├── main
│ └── java
│ ├── abscommon/ # Базовые классы
│ ├── components/ # Компоненты UI
│ ├── modules/ # Guice-модули
│ └── pages/ # PageObject страницы
└── test
└── java
└── otus/ # Тесты
## Запуск тестов
```bash
mvn clean test
Как добавить новые тесты
Создать новый PageObject в src/main/java/pages.

Добавить компонент/попап при необходимости.

Написать тест в src/test/java.
