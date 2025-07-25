package ru.yavshok.app.Tests.MainScreenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Pages.LoginScreenPage
import ru.yavshok.app.Pages.MainScreenPage
import ru.yavshok.app.Utils.testData

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage

    @Before
    fun setUp(){
        mainScreen = MainScreenPage(composeRuleActivity)
        loginScreen = LoginScreenPage(composeRuleActivity)
    }

    @Test
    fun existEmail(){
        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
            .displayedEmailTextField()
            .verifeInputTextInEmailTextField(testData.VALID_EMAIL)
            .displayedCheckButton()
            .clickCheckButton()
            .timeoutForGif()
            .displayedLineEmailTrue()
            .displayedImageCheckEmail()
    }

    @Test
    fun notExistEmail(){
        val email = Faker().internet().emailAddress()

        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
            .displayedEmailTextField()
            .verifeInputTextInEmailTextField(email)
            .displayedCheckButton()
            .clickCheckButton()
            .timeoutForFalseLine()
            .displayedLineEmailFalse()
    }

    @Test
    fun emailVerefication(){

        val email = Faker().internet().emailAddress()

        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
            .displayedEmailTextField()
            .verifeInputTextInEmailTextField(testData.VALID_EMAIL)
            .displayedCheckButton()
            .clickCheckButton()
            .timeoutForGif()
            .displayedLineEmailTrue()
            .displayedImageCheckEmail()
            .clearEmailTextField()
            .verifeInputTextInEmailTextField(email)
            .displayedCheckButton()
            .clickCheckButton()
            .timeoutForFalseLine()
            .displayedLineEmailFalse()
    }

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen(){
        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
            .displayedLoginButton()
            .clickLoginButton()
        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()

    }
}