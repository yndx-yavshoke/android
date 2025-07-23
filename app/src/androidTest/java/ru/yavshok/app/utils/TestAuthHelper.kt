package ru.yavshok.app.utils

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

object TestAuthHelper {
    fun loginAndPrepareUser(
        context: Context,
        email: String,
        password: String
    ): Pair<TokenStorage, UserStore> = runBlocking {
        val apiService = NetworkModule.provideApiService()
        val authRepository = AuthRepository(apiService)
        val tokenStorage = TokenStorage(context)
        val userStore = UserStore(authRepository, tokenStorage)

        val loginResponse = authRepository.login(email, password)
        require(loginResponse.isSuccessful) { "Login failed: ${loginResponse.code()}" }
        val loginBody = loginResponse.body() ?: error("No login body")
        val token = loginBody.token
        val user = loginBody.user

        tokenStorage.saveToken(token)
        userStore.setUser(user)

        tokenStorage to userStore
    }
}