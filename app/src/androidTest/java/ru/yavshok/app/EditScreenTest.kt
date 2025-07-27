package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.github.javafaker.Faker

@RunWith(AndroidJUnit4::class)
class EditScreenTest : BaseNavigationTest() {

    class Faker {
        val faker = Faker()
        val randomString = faker.lorem().characters(10)
    }

    fun shouldNavigateToEdit() = with(composeRule) {
        waitForMainScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        clickLoginButton()
        waitForLoginScreen()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()

        composeRule.onNodeWithTag(Tags.LoginScreen.emailInput).performTextInput("test@yavshok.ru")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordInput).performTextInput("QWErty!1")
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).performClick()
    }

    @Test
    fun shouldCheckEditScreenElements() {
        shouldNavigateToEdit()

        composeRule.onNodeWithTag(Tags.EditScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditScreen.nameInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditScreen.saveChange).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditScreen.backButton).assertIsDisplayed()
    }

    @Test
    fun shouldCheckEditName() {
        shouldNavigateToEdit()

        composeRule.onNodeWithTag(Tags.EditScreen.nameInput).performTextInput(randomString)
        composeRule.onNodeWithTag(Tags.EditScreen.saveChange).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.profileName).assertIsDisplayed(randomString)
    }

    @Test
    fun shouldCheckCancel() {
        shouldNavigateToEdit()

        composeRule.onNodeWithTag(Tags.EditScreen.nameInput).performTextInput(randomString)
        composeRule.onNodeWithTag(Tags.EditScreen.backButton).performClick()
    }
}
