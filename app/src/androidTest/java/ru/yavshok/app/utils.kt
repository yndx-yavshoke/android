package ru.yavshok.app
import com.github.javafaker.Faker


object Constants {
    const val REGISTER_EMAIL = "nekonekoneko@mail.ru"
    const val REGISTER_PASSWORD = "123456"
    const val LOGIN_AGE_STRING = "пятьдесятдва"
    const val YOUNG_EMAIL = "youngnekoneko@mail.ru"
    const val ADULT_EMAIL = "middlenekoneko@mail.ru"
    const val OLD_EMAIL = "oldnekoneko@mail.ru"
}

private val faker = Faker()

object FakeDataGenerator {

    fun generateName(): String {
        return faker.name().fullName()
    }

    fun generateEmail(): String {
        return faker.internet().emailAddress()
    }

    fun generatePassword(): String {
        return faker.internet().password(8, 16, true, true)
    }

    fun generateAge(): String {
        return faker.number().numberBetween(0, 99).toString()
    }

    fun generateVeryLongName(): String {
        val faker = Faker()
        return buildString {
            append(faker.name().fullName())
            while (length <= 50) {
                append(" ").append(faker.name().firstName())
            }
        }
    }

}

