package ru.yavshok.app.utils

import io.github.cdimascio.dotenv.dotenv


object EnvData {

    private val dotenv = dotenv {
        ignoreIfMissing = false
        directory = "/assets"
        filename = "env"
    }

    val email: String = dotenv["TEST_EMAIL"] ?: ""
    val password: String = dotenv["TEST_PASSWORD"] ?: ""

}
