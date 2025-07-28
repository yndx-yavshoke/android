package ru.yavshok.app.utils

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

object setupAuth {
    fun loginUser(context: Context, email: String, password: String) {
        runBlocking {
            val apiService = NetworkModule.provideApiService()
            val authRepository = AuthRepository(apiService)
            val tokenStorage = TokenStorage(context)
            val userStore = UserStore(authRepository, tokenStorage)

            val response = authRepository.login(email, password)
            check(response.isSuccessful) { "Login failed: code=${response.code()}, message=${response.message()}" }
            val data  = response.body() ?: error("Empty login response body")

            tokenStorage.saveToken(data.token)
            userStore.setUser(data.user)
        }
    }
}