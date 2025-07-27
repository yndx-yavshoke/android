package ru.yavshok.app.navigationTests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.pages.RegisterScreenPage
import kotlin.math.log

@RunWith(AndroidJUnit4::class)
class RegisterScreenNavigationTest {

    @get: Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var mainScreen: MainScreenPage

    @Before
    fun setup() {
        loginScreen = LoginScreenPage(composeRuleActivity)
        registerScreen = RegisterScreenPage(composeRuleActivity)
        mainScreen = MainScreenPage(composeRuleActivity)
    }

    @Test
    fun navigateFromRegisterToLogin() {
        mainScreen.waitForTitle()
        mainScreen.title.assertIsDisplayed()
        mainScreen.goToLoginButton.performClick()

        loginScreen.waitForTitle()
        loginScreen.title.assertIsDisplayed()
        mainScreen.title.assertDoesNotExist()

        loginScreen.clickGoToRegisterButton()
        registerScreen.title.assertIsDisplayed()
        loginScreen.title.assertDoesNotExist()

        registerScreen.clickBackButton()
        loginScreen.title.assertIsDisplayed()
        registerScreen.title.assertDoesNotExist()
    }
}