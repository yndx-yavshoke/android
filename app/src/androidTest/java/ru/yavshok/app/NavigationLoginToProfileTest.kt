package ru.yavshok.app

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.setupAuth

class NavigationLoginToProfileTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainScreen by lazy { MainScreenPage(composeRule) }
    private val loginScreen by lazy { LoginScreenPage(composeRule) }
    private val profileScreen by lazy { ProfileScreenPage(composeRule) }

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        setupAuth.loginUser(context, "valerii.mrm@yandex.ru", "qwerty123")
    }

    @Test
    fun shouldNavigateFromLoginToProfileScreenWithExistingUser() {

        mainScreen.waitExistTitle()
        mainScreen.clickLogin()

        loginScreen.waitExistTitle()
        loginScreen.login("valerii.mrm@yandex.ru", "qwerty123")

        profileScreen.waitEditButton()
        profileScreen.checkUserNameText("Janet Murazik")
    }
}