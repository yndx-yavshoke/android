package ru.yavshok.app.utils

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

object TestHelper {
    fun loginAndPrepareUser(
    private const val TEST_MODE_FLAG = "IS_TEST_MODE"
    private const val MIN_PASSWORD_LENGTH = 6

    fun loginAndPrepareUser(
        context: Context,
        email: String,
        password: String
    ): Pair<TokenStorage, UserStore> = runBlocking {
        require(email.isNotBlank() && email.contains("@")) { "Invalid email format" }
        require(password.length >= MIN_PASSWORD_LENGTH) { "Password too short" }

        val apiService = NetworkModule.provideApiService()
        val authRepository = AuthRepository(apiService)
        val tokenStorage = TokenStorage(context)
        val userStore = UserStore(authRepository, tokenStorage)

        authRepository.login(email, password)
            .takeIf { it.isSuccessful }
            ?.body()
            ?.let { response ->
                tokenStorage.saveToken(response.token)
                userStore.setUser(response.user)
                tokenStorage to userStore
            } ?: throw IllegalStateException("Login failed for $email")
    }

    fun launchMainActivityInTestMode(): ActivityScenario<MainActivity> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return ActivityScenario.launch(
            Intent(context, MainActivity::class.java).apply {
                putExtra(TEST_MODE_FLAG, true)
            }
        )
    }

    fun createTestMainActivityIntent(): Intent {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Intent(context, MainActivity::class.java).apply {
            putExtra(TEST_MODE_FLAG, true)
        }
    }
}