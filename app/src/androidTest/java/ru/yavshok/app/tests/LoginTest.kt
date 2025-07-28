package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.RegistrationPage

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val registrationPage = RegistrationPage(composeRule)

    @Before
    fun setup() {
        mainPage.waitForTitle()
        mainPage.clickLoginButton()

    }


    @Test
    fun testEmptyEmail() {

        val userPassword = Constants.REGISTER_PASSWORD
        loginPage.waitForTitle()
        loginPage.writeEmailPassword("", userPassword)
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @Test
    fun testEmptyPassword() {

        val userEmail = Constants.REGISTER_EMAIL
        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, "")
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @Test
    fun testWrongPassword() {

        val userEmail = Constants.REGISTER_EMAIL
        val userPassword = FakeDataGenerator.generatePassword()

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @Test
    fun testNotRegisterUser() {

        val userEmail = FakeDataGenerator.generateEmail()
        val userPassword = FakeDataGenerator.generatePassword()
        loginPage.waitForTitle()

        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateFromLoginScreenToRegistrationScreen() {

        loginPage.waitForTitle()
        loginPage.clickRegisterButton()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
fun navigateFromLoginScreenToMainScreen() {
        loginPage.waitForTitle()
        loginPage.clickBackButton()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
    }



}