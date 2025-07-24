# UI-тесты для приложения "Я в ШОКе"

UI-тесты для Android-приложения на Jetpack Compose.

## Структура проекта

- `tests/` — UI-тесты экранов
- `fixtures/` — Page Object фикстуры
- `test_utils/` — утилиты и тестовые данные

## Настройка

Создайте файл `test.properties` в корне проекта с рандомными данными:
```
testEmail=testusernew@mail.ru
testPassword=123456
testAge=20
```

## Запуск тестов

### Рекомендуемый порядок:

1. **Регистрация пользователя:**
```sh
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.yavshok.app.tests.RegisterUserFromPropertiesTest
```

2. **Остальные тесты:**
```sh
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.notClass=ru.yavshok.app.tests.RegisterUserFromPropertiesTest
```

**Примечание:** Поэтапный запуск обеспечивает стабильность тестов и предотвращает засорение базы данных тренировочного приложения лишними тестовыми пользователями.

## Вспомогательные функции
- `waitForTag(tag: String)` — ждет появления элемента с testTag
- `loginThroughUI(rule, email, password)` — авторизация через UI
- `logoutThroughUI(rule)` — выход из аккаунта через UI






