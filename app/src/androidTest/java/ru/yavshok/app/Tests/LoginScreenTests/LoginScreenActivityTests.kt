package ru.yavshok.app.Tests.LoginScreenTests

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Screens.LoginScreenPage
import ru.yavshok.app.Screens.MainScreenPage
import ru.yavshok.app.Screens.ProfileScreenPage
import ru.yavshok.app.Screens.RegisterScreenPage
import ru.yavshok.app.Utils.testData


@RunWith(AndroidJUnit4::class)
class LoginScreenActivityTests {

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
    fun errorLoginEmailOrPassword(){
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password()

        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedPasswordTextField()
            .inputPassword(password)
            .displayedButtonInShock()
            .clickButtonInShock()
            .displayedError()
            .errorEmailOrPassword()
    }

    @Test
    fun errorFormatEmail(){
        val badEmail = Faker().internet().emailAddress().replace("@", "")
        val password = Faker().internet().password()

        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .inputEmail(badEmail)
            .displayedPasswordTextField()
            .inputPassword(password)
            .displayedButtonInShock()
            .clickButtonInShock()
            .displayedError()
            .errorEmail()
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

    @Test
    fun successfulLogin(){
        loginScreen
            .timeoutTitle()
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .inputEmail(testData.VALID_EAMIL)
            .displayedPasswordTextField()
            .inputPassword(testData.VALID_PASSWORD)
            .displayedButtonInShock()
            .clickButtonInShock()
        profileScreen
            .timeoutForUserName()
            .displayedLogout()
            .clickLogoutButton()
    }
}