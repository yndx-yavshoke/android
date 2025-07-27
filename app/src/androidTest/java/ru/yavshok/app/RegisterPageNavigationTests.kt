package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runners.Parameterized
import ru.yavshok.app.modules.ProfilePage
import ru.yavshok.app.modules.RegisterPage


@OptIn(ExperimentalTestApi::class)
class RegisterPageNavigationTests {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var page : RegisterPage
    @Before
    fun setup(){
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 8000L, matcher = hasTestTag(Tags.MainScreen.loginButton))
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.RegisterScreen.screenTitle))
        page = RegisterPage(composeRule)
    }

    @Test
    fun shouldGoToNewUserProfileAfterRegistration(){
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password()
        val age = Faker().number().numberBetween(0, 100).toString()
        with(page) {
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            isVisibleAgeTextField()
            fillEmail(email)
            fillPassword(password)
            fillAge(age)
            isVisibleRegisterButton()
            clickRegister()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.ProfileScreen.nameText)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()
    }
}

