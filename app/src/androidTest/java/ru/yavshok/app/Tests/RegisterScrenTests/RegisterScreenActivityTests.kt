package ru.yavshok.app.Tests.RegisterScrenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Ignore
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
class RegisterScreenActivityTests {

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
    fun errorRepeatEmail(){
        val email = testData.VALID_EAMIL
        val password = Faker().internet().password(6,20)
        val age = Faker().random().nextInt(0,99).toString()

        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedPasswordTextField()
            .inputPassword(password)
            .displayedAgeTextField()
            .inputAge(age)
            .displayedButtonRegister()
            .clickRegisterButton()
            .displayedError()
            .errorRepeatEmail()
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

    @Ignore("Тест проходит успешно, игнорирую для того чтобы не забивать базу")
    @Test
    fun successfulRegistration(){
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6,20)
        val age = Faker().random().nextInt(0,99).toString()

        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedPasswordTextField()
            .inputPassword(password)
            .displayedAgeTextField()
            .inputAge(age)
            .displayedButtonRegister()
            .clickRegisterButton()
        profileScreen
            .displayedUserName()
            .userNameIsNeko()
            .clickLogoutButton()
    }


}