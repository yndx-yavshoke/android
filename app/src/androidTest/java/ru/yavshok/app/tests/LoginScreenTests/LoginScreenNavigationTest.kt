package ru.yavshok.app.tests.LoginScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.NavigationRule
import ru.yavshok.helpers.assertExistsAndDisplayed
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists
import ru.yavshok.helpers.login

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenNavigationTest: NavigationRule() {

    private val titleLogin = Tags.LoginScreen.screenTitle
    private val backButton = Tags.LoginScreen.backButton
    private val registerButton = Tags.LoginScreen.registerButton

    private val titleMain = Tags.MainScreen.screenTitle
    private val loginButton = Tags.MainScreen.loginButton

    private val titleRegister = Tags.RegisterScreen.screenTitle

    private val profileImage = Tags.ProfileScreen.profileImage

    @Before
    fun setup() {
        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")

        composeRule.clickAndAssertExists(loginButton)
        composeRule.assertExistsAndDisplayed(titleLogin)
    }

    @Test
    fun goToMainScreenFromLoginScreen() {
        composeRule.clickAndAssertExists(backButton)

        composeRule.onNodeWithTag(backButton).assertDoesNotExist()
        composeRule.assertExistsAndDisplayed(titleMain)
    }

    @Test
    fun goToRegisterScreenFromLoginScreen() {
        composeRule.clickAndAssertExists(registerButton)

        composeRule.onNodeWithTag(registerButton).assertDoesNotExist()
        composeRule.assertExistsAndDisplayed(titleRegister)
    }

    @Test
    fun authorizationOfRegisteredUser() {
        val email = BuildConfig.EMAIL
        val password = BuildConfig.PASSWORD
        composeRule.login(email, password)

        composeRule.onNodeWithTag(loginButton).assertDoesNotExist()
        composeRule.assertExistsAndDisplayed(profileImage)
    }
}