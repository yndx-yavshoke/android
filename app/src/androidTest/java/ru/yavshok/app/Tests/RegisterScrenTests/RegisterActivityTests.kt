package ru.yavshok.app.Tests.RegisterScrenTests

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
class RegisterActivityTests {

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
        loginScreen.timeoutTitle()
        loginScreen.clickButtonRegister()
        registerScreen.timeoutForTitle()

    }

    @Test
    fun shouldNavigateFromRegisterScreenToLoginScreen(){
        registerScreen
            .displayedTitle()
            .clickBackButton()
        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
    }

}