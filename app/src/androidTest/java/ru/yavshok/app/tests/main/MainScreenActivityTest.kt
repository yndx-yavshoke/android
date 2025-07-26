package ru.yavshok.app.tests.main

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage


@RunWith(AndroidJUnit4::class)
class MainScreenActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainPage: MainPage
    private lateinit var loginPage: LoginPage

    @Before
    fun setUp(){
        mainPage = MainPage(composeRule)
        loginPage = LoginPage(composeRule)

        with(mainPage){
            allElementsIsDisplayed()
        }
    }

    @Test
    fun shouldRedirectToLoginAfterClickLoginButton() {
        with(mainPage){
            clickToLoginButton()
        }
        with(loginPage){
            allElementsIsDisplayed()
        }
    }
}