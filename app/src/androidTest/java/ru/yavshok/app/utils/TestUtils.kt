package ru.yavshok.app.utils

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import kotlinx.coroutines.runBlocking
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.data.store.UserStore

object TestUtils {
    fun loginUser(context: Context, email: String, password: String) : Pair<TokenStorage, UserStore> =
        runBlocking {
            val apiService = NetworkModule.provideApiService()
            val authRepository = AuthRepository(apiService)
            val tokenStorage = TokenStorage(context)
            val userStore = UserStore(authRepository, tokenStorage)

            val response = authRepository.login(email, password)
            check(response.isSuccessful) { "Login failed: code=${response.code()}, message=${response.message()}" }
            val data  = response.body() ?: error("Empty login response body")

            tokenStorage.saveToken(data.token, true)
            userStore.setUser(data.user)

            tokenStorage to userStore
        }

    fun disableAnimations() {
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            executeShellCommand("settings put global transition_animation_scale 0.0")
            executeShellCommand("settings put global window_animation_scale 0.0")
            executeShellCommand("settings put global animator_duration_scale 0.0")
        }
    }

    fun launchMainActivityInTestMode(): ActivityScenario<MainActivity> {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
            putExtra("IS_TEST_MODE", true)
        }
        return ActivityScenario.launch(intent)
    }
}