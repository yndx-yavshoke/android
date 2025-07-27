package ru.yavshok.app.Tests.LoginScreenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Pages.LoginScreenPage
import ru.yavshok.app.Pages.MainScreenPage
import ru.yavshok.app.Pages.ProfileScreenPage
import ru.yavshok.app.Pages.RegisterScreenPage


@RunWith(AndroidJUnit4::class)
class LoginActivityTests {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var profileScreen: ProfileScreenPage

    @Before
    fun setUp(){

        mainScreen = MainScreenPage(composeRuleActivity)
        loginScreen = LoginScreenPage(composeRuleActivity)
        registerScreen = RegisterScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)

        mainScreen.timeoutFotTitle()
        mainScreen.clickLoginButton()
    }

    @Test
    fun shouldNavigateFromLoginScreenToMainScreen() {
        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
            .displayedButtonBack()
            .clickButtonBack()
        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
    }

    @Test
    fun shouldNavigateFromLoginScreenToRegisterScreen(){
        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
            .displayedButtonRegister()
            .clickButtonRegister()
        registerScreen
            .timeoutForTitle()
            .displayedTitle()
    }
}