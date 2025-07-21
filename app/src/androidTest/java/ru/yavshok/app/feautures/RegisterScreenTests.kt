package ru.yavshok.app.feautures

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.FakerGenerator.generateRandomAge
import ru.yavshok.app.utils.FakerGenerator.generateRandomEmail
import ru.yavshok.app.utils.FakerGenerator.generateRandomPassword
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.viewmodel.RegisterViewModel

@RunWith(AndroidJUnit4::class)
class RegisterScreenTestsWithContent {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<ComponentActivity>()

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val registerViewModel = factory.create(RegisterViewModel::class.java)
        composeTestRule.setContent {
            RegisterScreen(
                onNavigateBack = {},
                onRegistrationSuccess = {},
                viewModel = registerViewModel
            )
        }
        appPages = AppPages(composeTestRule)
    }

    @Test
    fun shouldNotRegisterWithoutEmail() {
        appPages.registerScreen.register("", generateRandomPassword(), generateRandomAge())
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Заполните все поля").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun shouldNotRegisterWithoutPassword() {
        appPages.registerScreen.register(generateRandomEmail(), "", generateRandomAge())
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Заполните все поля").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun shouldNotRegisterWithoutAge() {
        appPages.registerScreen.register(generateRandomEmail(), generateRandomPassword(), "")
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Заполните все поля").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun shouldNotRegisterTheSameEmail() {
        appPages.registerScreen.register(Datas.User.email, Datas.User.password, generateRandomAge())
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Пользователь с таким email уже существует").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun checkAllElementsOnRegisterScreen() {
        appPages.registerScreen.title.assertIsDisplayed()
        appPages.registerScreen.emailField.assertIsDisplayed()
        appPages.registerScreen.emailField.assertTextContains("Введите email")
        appPages.registerScreen.passwordField.assertIsDisplayed()
        appPages.registerScreen.passwordField.assertTextContains("Пароль")
        appPages.registerScreen.ageField.assertIsDisplayed()
        appPages.registerScreen.ageField.assertTextContains("Возраст")
        appPages.registerScreen.backButton.assertIsEnabled()
        appPages.registerScreen.backButton.assertTextContains("Назад")
        appPages.registerScreen.registerButton.assertIsEnabled()
        appPages.registerScreen.registerButton.assertTextContains("Зарегистрироваться")
    }
}

@RunWith(AndroidJUnit4::class)
class RegisterScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<MainActivity>()

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        appPages = AppPages(composeTestRule)
    }

    @Ignore("Пропускаем этот тест при общем запуске")
    @Test
    fun shouldRegisterNewUser() {
        composeTestRule.waitUntil(15000) {
            try {
                appPages.mainScreen.title.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        appPages.mainScreen.clickToLogin()
        composeTestRule.waitUntil(5000) {
            try {
                appPages.loginScreen.title.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        appPages.loginScreen.clickRegister()
        composeTestRule.waitUntil(5000) {
            try {
                appPages.registerScreen.title.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        appPages.registerScreen.register(generateRandomEmail(), generateRandomPassword(), generateRandomAge())
        composeTestRule.waitUntil(5000) {
            try {
                appPages.profileScreen.catName.assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        appPages.profileScreen.catAge.assertIsDisplayed()
    }
}