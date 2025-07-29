package ru.yavshok.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.screens.MainScreen
import com.github.javafaker.Faker

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var mainScreen: MainScreen
    private lateinit var loginScreen: LoginScreen

    private var faker = Faker()

    @Before
    fun setUp(){
        mainScreen = MainScreen(composeRule)
        loginScreen = LoginScreen(composeRule)

        with(mainScreen) {
            waitToLoad()
            clickLoginButton()
        }

        with(loginScreen) {
            waitToLoad()
        }
    }

    @Test
    fun shouldDisplayedAllViewsOnLoginScreen() {
        with(loginScreen) {
            checkScreenTitleIsDisplayed()
            checkEmailTextFieldIsDisplayed()
            checkPasswordTextFieldIsDisplayed()
            checkLoginButtonIsDisplayed()
            checkBackButtonIsDisplayed()
            checkRegisterButtonIsDisplayed()
        }
    }

    @Test
    fun shouldTypeEmailOnLoginScreen(){
        val emailName = "my@yandex.ru"
        with(loginScreen) {
            checkEmailTextFieldIsDisplayed()
            enterEmail(emailName)
            assertEmailIsDisplayed(emailName)
        }
    }

    @Test
    fun shouldShowErrorLabel(){
        val emailName = faker.internet().emailAddress()
        val password = faker.internet().password(6, 20)

        with(loginScreen) {
            waitToLoad()
            checkEmailTextFieldIsDisplayed()
            checkPasswordTextFieldIsDisplayed()
            enterEmail(emailName)
            assertEmailIsDisplayed(emailName)
            enterPassword(password)
            clickLoginButton()
            assertErrorLabelIsDisplayed()
        }
    }
}