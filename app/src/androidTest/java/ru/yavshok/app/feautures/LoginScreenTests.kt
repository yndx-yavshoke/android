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
import ru.yavshok.app.screens.LoginScreenPageObject
import ru.yavshok.app.screens.MainScreenPageObject
import ru.yavshok.app.screens.ProfileScreenPageObject
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.viewmodel.LoginViewModel


@RunWith(AndroidJUnit4::class)
class LoginScreenTestsWithContent {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<ComponentActivity>()

    private lateinit var loginScreen: LoginScreenPageObject

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
        loginScreen = LoginScreenPageObject(composeTestRule)
    }

    @Test
    fun shouldNotEnterWithWrongPassword() {
        loginScreen.loginShock("example@mail.ru", "WrongPassword")
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Неправильный email или пароль")
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun shouldNotEnterWithWrongEmail() {
        loginScreen.loginShock("not_existed_email@mail.ru", "WrongPassword")
        composeTestRule.waitUntil(5000) {
            try {
                composeTestRule.onNodeWithText("Неправильный email или пароль")
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun checkAllElementsOnLoginScreen() {
        loginScreen.title.assertIsDisplayed()
        loginScreen.emailField.assertIsDisplayed()
        loginScreen.emailField.assertTextContains("Email")
        loginScreen.passwordField.assertIsDisplayed()
        loginScreen.passwordField.assertTextContains("Пароль")
        loginScreen.backButton.assertIsEnabled()
        loginScreen.backButton.assertTextContains("Назад")
        loginScreen.registerButton.assertIsEnabled()
        loginScreen.registerButton.assertTextContains("Регистрация")
        loginScreen.loginButton.assertIsEnabled()
        loginScreen.loginButton.assertTextContains("В шок")
    }
}

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<MainActivity>()

    private lateinit var loginScreen: LoginScreenPageObject
    private lateinit var profileScreen: ProfileScreenPageObject
    private lateinit var mainScreen: MainScreenPageObject

    @Before
    fun setUp() {
        loginScreen = LoginScreenPageObject(composeTestRule)
        profileScreen = ProfileScreenPageObject(composeTestRule)
        mainScreen = MainScreenPageObject(composeTestRule)
    }

    @Test
    fun shouldEnterProfile() {
        composeTestRule.waitUntil(15000) {
            try {
                mainScreen.title.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        mainScreen.clickToLogin()
        composeTestRule.waitUntil(5000) {
            try {
                loginScreen.title.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        loginScreen.loginShock("example@mail.ru", "Pass123")
        composeTestRule.waitUntil(5000) {
            try {
                profileScreen.catName.assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        profileScreen.catAge.assertIsDisplayed()
    }
}