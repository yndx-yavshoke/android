package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.LoginViewModel
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private fun openLoginScreen(){
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }

    @Test
    fun shouldTypeEmailAndPasswordOnLoginScreen() {
        val testUser = TestDataId.registeredUser
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
//  Вводим в поля ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .performTextInput(testUser.password)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .assertTextContains(testUser.email)
        val bulletMask = "\u2022".repeat(testUser.password.length)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .assertTextContains(bulletMask)
    }

    @Test
    fun shouldTLoginNotExistsUserDataOnLoginScreen() {
        val testUser = TestDataId.nonRegisteredUser
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
//  Вводим в поля ввода данные не зарег пользовтеля
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .performTextInput(testUser.password)
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .assertTextContains(testUser.email)
        val bulletMask = "\u2022".repeat(testUser.password.length)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .assertTextContains(bulletMask)
//  Кликаем по loginButton
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.errorTextMessage))
//  Проверяем что отобразился errorTextMessage
        composeRule.onNodeWithTag(Tags.LoginScreen.errorTextMessage).assertIsDisplayed()
    }

    @Test
    fun shouldNotPassEmptyFieldOnLoginScreen() {
        openLoginScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
//  Поля ввода оставляем пустыми
//  Кликаем по loginButton
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.errorTextMessage)
        )
//  Проверяем что отобразился errorTextMessage
        composeRule.onNodeWithTag(Tags.LoginScreen.errorTextMessage).assertIsDisplayed()
    }
}



