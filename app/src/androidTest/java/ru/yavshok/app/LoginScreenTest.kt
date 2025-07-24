package ru.yavshok.app.ru.yavshok.app

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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.TestAuthCredentials
import ru.yavshok.app.ui.screens.profile.ProfileScreen


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {


    @get:Rule
    //val composeRule = createAndroidComposeRule<MainActivity>()

    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private lateinit var testEmail: String

    @Before
    fun setUp() {
        testEmail = TestDataGenerator.generateRandomEmail()
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

    }

    @Test
    fun shouldTypeEmailOnLoginScreen() {

        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).assertTextContains(testEmail)
    }

    @Test
    fun shouldDisplayAllElementsInInitialState() {
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.screenContainer).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.registrationButton).assertIsDisplayed()
    }

    @Test
    fun shouldDisplayErrorAfterFillNotRealEmail() {
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.emailField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntil(1000) {
            try {
                composeRule.onNodeWithTag(Tags.LoginScreen.textMessageError).assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        composeRule.onNodeWithTag(ru.yavshok.app.Tags.LoginScreen.textMessageError).assertIsDisplayed()
    }



}

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTestCorrectData {


    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldDisplayNavigateToProfileScreenAfterSuccessLogin() {
        try {
            composeRule.waitUntil(10000) {
                try {
                    composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertExists()
                    true
                } catch (e: AssertionError) {
                    println("Ошибка: Не удалось найти заголовок главного экрана (${Tags.MainScreen.screenTitle})")
                    false
                }
            }

            composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

            composeRule.waitUntil(10000) {
                try {
                    composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertExists()
                    true
                } catch (e: AssertionError) {
                    println("Ошибка: Не удалось найти заголовок экрана входа (${Tags.LoginScreen.screenTitle})")
                    false
                }
            }

            try {
                composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
                composeRule.onNodeWithTag(Tags.LoginScreen.emailField).assertIsDisplayed()
            } catch (e: AssertionError) {
                throw AssertionError("Ошибка проверки элементов экрана входа: ${e.message}")
            }

            try {
                composeRule.onNodeWithTag(Tags.LoginScreen.emailField)
                    .performTextInput(TestAuthCredentials.REGISTERED_USER_EMAIL)
                composeRule.onNodeWithTag(Tags.LoginScreen.passwordField)
                    .performTextInput(TestAuthCredentials.REGISTERED_USER_PASSWORD)
                composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
            } catch (e: Exception) {
                throw AssertionError("Ошибка при вводе данных: ${e.message}")
            }

            // Шаг 3: Ожидание экрана профиля
            composeRule.waitUntil(10000) {
                try {
                    composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertExists()
                    true
                } catch (e: AssertionError) {
                    println("Ошибка: Не удалось найти имя пользователя (${Tags.ProfileScreen.nameText})")
                    false
                }
            }

            try {
                composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()
            } catch (e: AssertionError) {
                throw AssertionError("Ошибка отображения экрана профиля: ${e.message}")
            }

        } catch (e: AssertionError) {
            println("\n❌ Тест упал с ошибкой: ${e.message}")
            throw e
        }
    }
}