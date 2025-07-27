package ru.yavshok

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags

@RunWith(AndroidJUnit4::class)
class AppFlowTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainScreen_showsTitle() {
        composeTestRule.onNodeWithText("Я в ШОКе").assertIsDisplayed()
    }

    @Test
    fun loginFlow_opensProfileScreen() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Пароль").performTextInput("12345")
        composeTestRule.onNodeWithText("В шок").performClick()
        composeTestRule.waitUntilExists(hasText("Edit Profile"), 5_000)
        composeTestRule.onNodeWithText("Edit Profile").assertIsDisplayed()
    }

    @Test
    fun registrationFlow_opensProfileScreen() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeTestRule.onNodeWithText("Регистрация").performClick()
        composeTestRule.onNodeWithText("Введите email").performTextInput("newuser@test.com")
        composeTestRule.onNodeWithText("Пароль").performTextInput("12345")
        composeTestRule.onNodeWithText("Возраст").performTextInput("25")
        composeTestRule.onNodeWithText("Зарегистрироваться").performClick()
        composeTestRule.waitUntilExists(hasText("Edit Profile"), 5_000)
        composeTestRule.onNodeWithText("Edit Profile").assertIsDisplayed()
    }

    @Test
    fun mainScreen_showsErrorForUnregisteredEmail() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("test@test.ru")
        composeTestRule.onNodeWithText("Я в шоке?").performClick()
        composeTestRule.onNodeWithText("Ты еще не в ШОКе").assertIsDisplayed()
    }

    @Test
    fun mainScreen_showsSuccessForRegisteredEmail() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("test1@test.ru")
        composeTestRule.onNodeWithText("Я в шоке?").performClick()
        composeTestRule.onNodeWithText("Ты уже в ШОКе").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Happy Cat").assertIsDisplayed()
    }

    @Test
    fun loginScreen_showsErrorForInvalidEmail() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeTestRule.onNodeWithText("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithText("Пароль").performTextInput("12345")
        composeTestRule.onNodeWithText("В шок").performClick()
        composeTestRule.onNodeWithText("Неверный формат email").assertIsDisplayed()
    }

    @Test
    fun registrationScreen_showsErrorForInvalidEmail() {
        composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeTestRule.onNodeWithText("Регистрация").performClick()
        composeTestRule.onNodeWithText("Введите email").performTextInput("invalid-email")
        composeTestRule.onNodeWithText("Пароль").performTextInput("12345")
        composeTestRule.onNodeWithText("Возраст").performTextInput("25")
        composeTestRule.onNodeWithText("Зарегистрироваться").performClick()
        composeTestRule.onNodeWithText("Неверный формат email").assertIsDisplayed()
    }
}

fun ComposeTestRule.waitUntilExists(matcher: SemanticsMatcher, timeoutMillis: Long) {
    this.waitUntil(timeoutMillis) {
        this.onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()
    }
} 