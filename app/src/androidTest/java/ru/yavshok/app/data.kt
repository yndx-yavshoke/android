package ru.yavshok.app

import io.github.cdimascio.dotenv.dotenv


object ValidUser {
    private val dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }

    val EMAIL: String = dotenv["EMAIL"] ?: "testtest@test.test"
    val PASSWORD: String = dotenv["PASSWORD"] ?: "123456"
    val WRONG_PASSWORD: String = dotenv["WRONG_PASSWORD"] ?: "654321"
    val DEFAULT_NAME: String = dotenv["DEFAULT_NAME"] ?: "Tester"
    val EDIT_NAME: String = dotenv["EDIT_NAME"] ?: "Edit tester"
}