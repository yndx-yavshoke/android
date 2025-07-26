package ru.yavshok.app.utils

import io.github.serpro69.kfaker.faker

object Datas {
    object User {
        const val email = "example@mail.ru"
        const val password = "Pass123"
    }
    object FakeUser {
        val faker = faker{}

        val ruFaker = faker{
            fakerConfig {
                locale = "ru"
            }
        }

        fun email(): String = faker.internet.email()

        fun password(): String {
            return faker.random.randomString(
                min = 6,
                max = 20,
                numericalChars = true
            )
        }

        fun age(): String = faker.random.nextInt(0..99).toString()

        fun name(): String = ruFaker.name.name()
    }
}