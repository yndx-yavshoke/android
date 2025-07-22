package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class LoginScreenNavigationTest {

//  Tags.LoginScreen.screenTitle
//  Tags.LoginScreenScreen.emailTextField
//  Tags.LoginScreen.passwordTextField
//  Tags.LoginScreen.errorMessageText

//  Tags.LoginScreen.loginButton
//  Tags.LoginScreen.backButton
//  Tags.LoginScreen.registrationButton

//  Tags.MainScreen.screenTitle
//  Tags.RegistrationScreen.screenTitle

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val testUser = TestDataId.registeredUser

//  Предзагрузка LoginScreen
    private fun openLoginScreen() {
//  Ожидание MainScreen, клик по loginButton
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
//  Ожидание LoginScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldNavigateFromLoginScreenToRegistrationScreen() {
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).assertIsDisplayed()
//  Клиеаем по registrationButton
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()
//  Ожидание RegistrationScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegistrationScreen.screenTitle))
//        Thread.sleep(999999)
//  Проверяем наличие титула на RegistrationScreen
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
//  Проверяем что LoginScreen исчезла
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
    }

    @Test
    fun shouldNavigateFromLoginScreenToMainScreen() {
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
//  Кликаем по backButton
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).performClick()

//  Ожидание MainScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))
//  Проверяем наличие титула на MainScreen
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
//      Thread.sleep(999999)
//  Проверяем что страница логина исчезла
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
    }

    @Test
    fun shouldNavigateFromLoginScreenToProfileScreen() {
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
//  Вводим в поля ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput(testUser.password)
//  Кликаем по loginButton
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
//  Ожидание ProfileScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton))
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
//  Проверяем что LoginScreen исчезла
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()
//        Thread.sleep(999999)
    }

}