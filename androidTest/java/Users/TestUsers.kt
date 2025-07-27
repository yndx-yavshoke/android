package Users

import java.util.UUID

object TestUsers {
    data class User(val email: String, val password: String, val age: Int)

    val uniqueId = UUID.randomUUID().toString().substring(0, 8)
    val oldUser = User("example@domen.ru", "12345678", 22)
    val newUser = User("test_$uniqueId@example.com", "12345678", 22)
    val newName = (1..8).joinToString("") { ('a'..'z').random().toString() }
}