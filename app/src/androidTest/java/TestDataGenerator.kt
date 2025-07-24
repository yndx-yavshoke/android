import kotlin.random.Random

object TestDataGenerator {

    // Случайный email
    fun generateRandomEmail(): String {
        val usernameLength = Random.nextInt(5, 15)
        val domainLength = Random.nextInt(3, 10)

        return buildString {
            append(generateRandomString(usernameLength, includeDigits = true))
            append("@")
            append(generateRandomString(domainLength, includeDigits = false))
            append(".com")
        }
    }

    // Случайный пароль
    fun generateRandomPassword(): String {
        val length = Random.nextInt(8, 16)
        return generateRandomString(
            length = length,
            includeUpperCase = true,
            includeDigits = true,
            includeSpecial = true
        )
    }


    // функция генерации строк
    private fun generateRandomString(
        length: Int,
        includeUpperCase: Boolean = false,
        includeDigits: Boolean = false,
        includeSpecial: Boolean = false
    ): String {
        val charPool = mutableListOf<CharRange>().apply {
            add('a'..'z')
            if (includeUpperCase) add('A'..'Z')
            if (includeDigits) add('0'..'9')
            if (includeSpecial) addAll(listOf('!'..'/', ':'..'@', '['..'`', '{'..'~'))
        }.flatMap { it.toList() }

        return List(length) { charPool.random() }.joinToString("")
    }
}