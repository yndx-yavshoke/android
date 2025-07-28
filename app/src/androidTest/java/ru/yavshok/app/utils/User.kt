package utils

import io.github.serpro69.kfaker.Faker


object User {

    private val faker = Faker()

    data class UserData(
        val email: String,
        val password: String,
        val age: String,
        val name: String = ""
    )

    fun generateRandomName(): String =
        faker.name.firstName()

    fun generateRandomUser(): UserData = UserData(
        email = faker.internet.email(),
        password = "A1b!C2d#",
        age = (0..99).random().toString(),
        name = ""
    )
}
