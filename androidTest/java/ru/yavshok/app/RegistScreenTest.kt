package ru.yavshok.app

import Pages.ShokRegistPage
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
class RegistScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var registrationPage: ShokRegistPage

    @Before
    fun TestSetup() {

        registrationPage = ShokRegistPage(composeRule)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.registButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    @Test
    fun shouldErrorWithoutData(){

        composeRule.onNodeWithTag(Tags.RegisterScreen.registButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.registButton).performClick()
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage).assertTextContains("Заполните все поля")
    }

    @Test
    fun shouldSuccessRegist(){

        registrationPage.register(
            TestUsers.newUser.email,
            TestUsers.newUser.password,
            TestUsers.newUser.age

        )
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editButton)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.registButton).assertDoesNotExist()

    }

    @Test
    fun shouldFailAgainstRegist(){

        registrationPage.register(
            TestUsers.oldUser.email,
            TestUsers.oldUser.password,
            TestUsers.oldUser.age

        )
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.errorMessage)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorMessage).assertTextContains("Пользователь с таким email уже существует")

    }
}
