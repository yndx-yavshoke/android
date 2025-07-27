package ru.yavshok.app

import Pages.ShokLoginPage
import Users.TestUsers
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var loginPage: ShokLoginPage

    @Before
    fun TestSetup() {

        loginPage = ShokLoginPage(composeRule)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }

    @Test
    fun shouldNotLoginWithoutData(){
        loginPage.login(
            email = "",
            password = ""
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertTextContains("Заполните все поля")
    }

    @Test
    fun shouldSuccessAuth() {
        loginPage.login(
            TestUsers.oldUser.email,
            TestUsers.oldUser.password
        )

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editButton)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.registButton).assertDoesNotExist()
        //composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertExists()
    }

    @Test
    fun shouldNotLoginWithWrongPassword() {
        loginPage.login(
            TestUsers.oldUser.email,
            password = "1111111111111111"
        )
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.errorMessage)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertTextContains("Неверный email или пароль")

    }

}