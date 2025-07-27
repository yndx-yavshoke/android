# Инструкция по настройке тестовых данных для авторизации

Для корректной работы инструментальных тестов, связанных с авторизацией, необходимо создать файл `UserData.kt` в этой папке (`app/src/androidTest/java/ru/yavshok/app/`).


## Пример содержимого файла `UserData.kt`:

```kotlin
package ru.yavshok.app

object UserData {
    const val TEST_EMAIL = "your_email@example.com"
    const val TEST_PASSWORD = "your_password"
}
```
