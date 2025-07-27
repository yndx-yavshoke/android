package faker

import com.github.javafaker.Faker

object FakeDataFactory {

    private val faker = Faker()

    fun createNewUser() : FakeUser {
        return FakeUser(
            email = faker.internet().emailAddress(),
            password = faker.internet().password(6,20,true, true),
            age = faker.number().numberBetween(0,101).toString()
        )
    }

    fun createInvalidEmailUser() : FakeUser {
       return createNewUser().copy(email = "invalid-email")
    }

    fun createWeakPasswordUser() : FakeUser {
        return createNewUser().copy(password = "123")
    }

    fun createWeakAgeUser() : FakeUser {
        return createNewUser().copy(age = "-1")
    }

    fun createWithEmptyFields() : FakeUser {
        return createNewUser().copy(
            email = "",
            password =  "",
            age = "",
        )
    }

    fun createInvalidEmail() : String {
        return faker.internet().emailAddress()
    }

    fun createValidEmail() : String {
        return "user123@yandex.ru"
    }

    fun createInvalidPassword() : String {
        return faker.internet().password(6,20, true, true)
    }

    fun createValidPassword() : String {
        return "123456"
    }
    fun createValidUsername() : String {
        return faker.name().username()
    }

    fun createEmptyUsername() : String {
        return ""
    }
}