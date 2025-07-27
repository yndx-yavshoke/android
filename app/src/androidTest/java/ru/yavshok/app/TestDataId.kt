package ru.yavshok

object TestDataId {
    val registeredUser = UserCredentials(
        email = "q@q.q",
        password = "123456"
    )

    val nonRegisteredUser = UserCredentials(
        email = "not_registered@ya.com",
        password = "somepassword"
    )
    fun generateUniqueEmail(): String {
        val timestamp = System.currentTimeMillis()
        return "test_$timestamp@ya.com"
    }
}

data class UserCredentials(
    val email: String,
    val password: String
)

object TestUtils {
    fun generateUniqueEmail(): String {
        val timestamp = System.currentTimeMillis()
        return "test_$timestamp@ya.com"
    }
}