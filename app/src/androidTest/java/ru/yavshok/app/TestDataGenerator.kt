package ru.yavshok.app


import java.util.UUID
import kotlin.random.Random
class TestDataGenerator {

    private val domains = listOf("gmail.com", "yandex.ru", "mail.ru", "example.com")

//  Генерируем случайный email
    fun generateEmail(): String {
        val uuid = UUID.randomUUID().toString().substring(0, 8)
        val domain = domains.random()
        return "user_$uuid@$domain"
    }
//  Генерируем случайный пароль длиной [length] символов (по умолчанию 10)
    fun generatePassword(length: Int = 10): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length).map { chars.random() }.joinToString("")
    }
//  Генерируем случайный возраст от 0 до 99
    fun generateAge(): Int = Random.nextInt(0, 100)


//  Генерируем полный набор данных пользователя
    fun generateUserData(): TestUserData = TestUserData(
        email = generateEmail(),
        password = generatePassword(),
        age = generateAge()
    )
}

// Данные пользователя для тестов
data class TestUserData(
    val email: String,
    val password: String,
    val age: Int
)

