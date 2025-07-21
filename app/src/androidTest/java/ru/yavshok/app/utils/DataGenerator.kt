package ru.yavshok.app.utils

object FakerGenerator {
    private val firstName = listOf("Иван", "Алексей", "Мария", "Елена", "Дмитрий")
    private val lastName = listOf("Иванов", "Петров", "Сидорова", "Смирнова", "Кузнецов")
    private val domains = listOf("mail.ru", "gmail.com", "yandex.ru", "icloud.com")

    fun generateRandomName(): String {
        return "${firstName.random()} ${lastName.random()}"
    }

    fun generateRandomEmail(): String {
        val timestamp = System.currentTimeMillis()
        val domain = domains.random()
        return "autotest_${timestamp}@${domains.random()}"
    }

    fun generateRandomPassword(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('!', '@', '#', '$')
        return (1..12).map { chars.random() }.joinToString("")
    }

    fun generateRandomAge(): String {
        return (0..99).random().toString()
    }
}