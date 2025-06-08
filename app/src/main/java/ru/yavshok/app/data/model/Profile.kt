package ru.yavshok.app.data.model

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val subtitle: String = "Ты молоденький котик",
    val postsCount: Int = 42,
    val followersCount: Int = 567,
    val likesCount: Int = 890,
    val profileImageUrl: String? = null,
    val photos: List<String> = emptyList()
) 