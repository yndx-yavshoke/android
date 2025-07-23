package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.viewmodel.ViewModelFactory
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.*
import ru.yavshok.app.ui.screens.register.RegisterScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private fun openRegistrationScreen(){
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }

    @Test
    fun shouldTypeEmailAndPasswordAndAgeOnRegisterScreen() {
        val testUser = TestDataGenerator().generateUserData()
        openRegistrationScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.RegistrationScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegistrationScreen.backButton).assertIsDisplayed()
//  Генерим данные пользователя в поля
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField).performTextInput(testUser.password)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField).performTextInput(testUser.age.toString())
//  Проверяем, что введённые данные отображаются
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).assertTextContains(testUser.email)
        val bulletMask = "\u2022".repeat(testUser.password.length)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField).assertTextContains(bulletMask)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField).assertTextContains(testUser.age.toString())
//        Thread.sleep(999999)
    }

    @Test
    fun shouldRegisterAlreadyExistUserDataOnRegisterScreen() {
        val testUser = TestDataId.registeredUser
        openRegistrationScreen()
//  Вводим в поля ввода данные зарег пользовтеля
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.passwordTextField).performTextInput(testUser.password)
        composeRule.onNodeWithTag(Tags.RegistrationScreen.ageTextField).performTextInput("30")
//  Проверяем, что введённые данные отображаются
        composeRule.onNodeWithTag(Tags.RegistrationScreen.emailTextField).assertTextContains(testUser.email)
//  Кликаем по registrationButton
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegistrationScreen.errorTextMessage))
//  Проверяем что отобразился errorTextMessage
        composeRule.onNodeWithTag(Tags.RegistrationScreen.errorTextMessage).assertIsDisplayed()

    }
    @Test
    fun shouldNotPassEmptyFieldOnRegisterScreen() {
        openRegistrationScreen()
//  Поля ввода оставляем пустыми
//  Кликаем по loginButton
        composeRule.onNodeWithTag(Tags.RegistrationScreen.registrationButton).performClick()
//  Ожидание прогрузки страницы
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegistrationScreen.errorTextMessage)
        )
//  Проверяем что отобразился errorTextMessage
        composeRule.onNodeWithTag(Tags.RegistrationScreen.errorTextMessage).assertIsDisplayed()
//        Thread.sleep(999999)
    }

}

