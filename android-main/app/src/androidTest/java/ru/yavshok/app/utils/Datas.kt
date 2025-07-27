package ru.yavshok.app.utils

import android.health.connect.datatypes.units.Length
import io.github.serpro69.kfaker.faker

object Datas {

    object User {
        const val PASSWORD = "Test@Password123" 
        
        const val EMAIL = "test.user@example.com"
        
        init {
            require(PASSWORD.length >= 8) { "Пароль должен содержать минимум 8 символов" }
            require(PASSWORD.any { it.isDigit() }) { "Пароль должен содержать цифры" }
            require(PASSWORD.any { it.isUpperCase() }) { "Пароль должен содержать заглавные буквы" }
            require(PASSWORD.any { !it.isLetterOrDigit() }) { "Пароль должен содержать спецсимволы" }
        }
    }

    object FakeUser {
        fun password(): String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + listOf('!', '@', '#', '$')
            return (1..12)
                .map { allowedChars.random() }
                .joinToString("")
                .also { 
                    require(it.length == 12) { "Сгенерированный пароль должен содержать 12 символов" }
                }
        }
    }
}