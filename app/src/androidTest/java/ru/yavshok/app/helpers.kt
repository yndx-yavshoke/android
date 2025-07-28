package ru.yavshok.app
import com.github.javafaker.Faker


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

object Constants {
    const val REGISTER_EMAIL = "dfdf@cs.su"
    const val REGISTER_PASSWORD = "qwerty"
    const val LOGIN_AGE_STRING = "два"
    const val YOUNG_EMAIL = "youngdfdf@cs.su"
    const val ADULT_EMAIL = "averagedfdf@cs.su"
    const val OLD_EMAIL = "olddfdf@cs.su"
}
