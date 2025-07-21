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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.FakerGenerator.generateRandomEmail
import ru.yavshok.app.utils.FakerGenerator.generateRandomPassword
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.viewmodel.LoginViewModel


@RunWith(AndroidJUnit4::class)
class LoginScreenTestsWithContent {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<ComponentActivity>()

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val loginViewModel = factory.create(LoginViewModel::class.java)
        composeTestRule.setContent {
            LoginScreen(
                onNavigateToRegister = {},
                onNavigateBack = {},
                onLoginSuccess = {},
                viewModel = loginViewModel
            )
        }
        appPages = AppPages(composeTestRule)
    }

    @Test
    fun shouldNotEnterWithWrongPassword() {
        appPages.loginScreen.loginShock(Datas.User.email, generateRandomPassword())
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Неверный email или пароль").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun shouldNotEnterWithWrongEmail() {
        appPages.loginScreen.loginShock(generateRandomEmail(), generateRandomPassword())
        composeTestRule.waitUntil(10000) {
            try {
                composeTestRule.onNodeWithText("Неверный email или пароль").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun checkAllElementsOnLoginScreen() {
        appPages.loginScreen.title.assertIsDisplayed()
        appPages.loginScreen.emailField.assertIsDisplayed()
        appPages.loginScreen.emailField.assertTextContains("Email")
        appPages.loginScreen.passwordField.assertIsDisplayed()
        appPages.loginScreen.passwordField.assertTextContains("Пароль")
        appPages.loginScreen.backButton.assertIsEnabled()
        appPages.loginScreen.backButton.assertTextContains("Назад")
        appPages.loginScreen.registerButton.assertIsEnabled()
        appPages.loginScreen.registerButton.assertTextContains("Регистрация")
        appPages.loginScreen.loginButton.assertIsEnabled()
        appPages.loginScreen.loginButton.assertTextContains("В шок")
    }
}

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<MainActivity>()

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        appPages = AppPages(composeTestRule)
    }

    @Test
    fun shouldEnterProfile() {
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
        appPages.loginScreen.loginShock(Datas.User.email, Datas.User.password)
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