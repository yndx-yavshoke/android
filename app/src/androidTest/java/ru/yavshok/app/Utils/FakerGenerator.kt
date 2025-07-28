package ru.yavshok.app.Utils

import io.github.serpro69.kfaker.faker

object DataGenerator {
    object TestUser {
        const val existingUserEmail = "vasilisa_28_05@mail.ru"
        const val existingUserPassword = "11111111"
    }
    object UserStatus {
        const val YOUNG_STATUS = "Молоденький котик"
        const val ADULT_STATUS = "Взрослый котик"
        const val OLD_STATUS = "Старый котик"
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

        fun youngAge(): String = faker.random.nextInt(0..21).toString()
        fun adultAge(): String = faker.random.nextInt(22..68).toString()
        fun oldAge(): String = faker.random.nextInt(69..99).toString()

        fun name(): String = ruFaker.name.name()
        fun longName(length: Int): String = ruFaker.random.randomString(length)

    }
}