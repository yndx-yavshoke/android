package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.LoginPage

@OptIn(ExperimentalTestApi::class)
class LoginPageNavigationTests {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var page : LoginPage
    @Before
    fun setup(){
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.LoginScreen.screenTitle))
        page = LoginPage(composeRule)
    }

    @Test
    fun shouldGoToMainPageAfterReturnClick(){
        with(page){
            isVisibleReturnButton()
            clickReturn()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.MainScreen.screenTitle))
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldGoToRegisterPageAfterRegisterClick(){
        with(page){
            isVisibleRegisterButton()
            clickRegister()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.RegisterScreen.screenTitle))
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldGoToProfilePageAfterSuccessLogin(){
        with(page){
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            fillEmail(dataEmail)
            fillPassword(dataPassword)
            isVisibleLoginButton()
            clickLogin()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.ProfileScreen.nameText))
        composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()
    }
}