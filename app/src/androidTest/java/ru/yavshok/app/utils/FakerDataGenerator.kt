package ru.yavshok.app.utils

import com.github.javafaker.Faker


object FakerDataGenerator {

    private val faker = Faker()

    fun randomEmail(): String = faker.internet().emailAddress()

    fun randomName(): String = faker.name().firstName()

    fun randomPassword(): String = faker.internet().password(5, 20)

    fun randomShortPassword(): String = faker.internet().password(1, 4)

    fun randomLongPassword(): String = faker.internet().password(21, 22)

    fun randomAge(): String = faker.random().nextInt(0, 99).toString()

}
