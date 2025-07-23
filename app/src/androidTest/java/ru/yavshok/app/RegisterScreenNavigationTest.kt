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
class RegisterScreenNavigationTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val testUser = TestDataGenerator().generateUserData()


    private fun openRegistrationScreen() {
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
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()
//  Ожидание RegistrationScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegistrationScreen.screenTitle)
        )
//  Проверяем наличие титула на RegistrationScreen
    composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldNavigateFromRegistrationScreenToLoginScreen() {
        openRegistrationScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.backButton).assertIsDisplayed()
//  Клиеаем по backButton
        composeRule.onNodeWithTag(Tags.RegistrationScreen.backButton).performClick()
//  Ожидание LoginScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))
//        Thread.sleep(999999)
//  Проверяем наличие титула на LoginScreen
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
//  Проверяем что RegistrationScreen исчезла
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertDoesNotExist()
    }

    @Test
    fun shouldNavigateFromRegistrationScreenToProfileScreen() {
        openRegistrationScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.backButton).assertIsDisplayed()
//  Генерим данные пользователя в поля
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField).performTextInput(testUser.password)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField).performTextInput(testUser.age.toString())
//  Кликаем по registrationButton
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
//  Ожидание ProfileScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton))
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
//  Проверяем что RegistrationScreen исчезла
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertDoesNotExist()
//  Выходим из ProfileScreen для других тестов
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()
    }
}