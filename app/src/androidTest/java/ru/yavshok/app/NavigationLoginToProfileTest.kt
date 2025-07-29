package ru.yavshok.app

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.TestData
import ru.yavshok.app.utils.TestUtils

@RunWith(AndroidJUnit4::class)
class NavigationLoginToProfileTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val profileScreen by lazy { ProfileScreenPage(composeRule) }
    private val mainScreen by lazy { MainScreenPage(composeRule)}
    private val loginScreen by lazy { LoginScreenPage(composeRule)}

    @Before
    fun setup() {
        TestUtils.disableAnimations()
        val context = ApplicationProvider.getApplicationContext<Context>()
        TokenStorage(context).logout()
        scenario = TestUtils.launchMainActivityInTestMode()
    }

    @Test
    @DisplayName("Навигация: Переход со страницы логина на страницу профиля")
    fun shouldNavigateFromLoginScreenToProfileScreen() {
        mainScreen.waitExistTitle()
        mainScreen.clickLogin()

        loginScreen.waitExistTitle()
        loginScreen.login(TestData.EXISTING_EMAIL, TestData.VALID_PASSWORD)

        profileScreen.waitEditButton()
    }
}