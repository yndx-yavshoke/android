package ru.yavshok.app.tests.register

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.RegisterPage



@RunWith(AndroidJUnit4::class)
class RegistrationScreenActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainPage: MainPage
    private lateinit var loginPage: LoginPage
    private lateinit var registerPage: RegisterPage

    @Before
    fun setUp(){
        mainPage = MainPage(composeRule)
        loginPage = LoginPage(composeRule)
        registerPage = RegisterPage(composeRule)

        with(mainPage){
            allElementsIsDisplayed()
            clickToLoginButton()
        }
        with(loginPage){
            allElementsIsDisplayed()
            clickRegistrationButton()
        }
        with(registerPage){
            allElementsIsDisplayed()
        }

    }

    @Test
    fun shouldRedirectToLoginAfterClickBackButton() {
        with(registerPage) {
            clickBackButton()
        }
        with(loginPage){
            allElementsIsDisplayed()
        }
    }
}