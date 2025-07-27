package ru.yavshok.app.Tests.RegisterScrenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Pages.RegisterScreenPage
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RegisterTests {
    @get:Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private lateinit var registerScreen: RegisterScreenPage

    @Before
    fun setUp(){
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerScreen = RegisterScreenPage(composeRule)
    }

    @Test
    fun displayedAllViewsRegisterPage(){

        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .displayedPasswordTextField()
            .displayedAgeTextField()
            .displayedButtonRegister()
            .displayedButtonBack()
    }

    @Test
    fun allPlaceholderInRegisterPage(){
        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .emailTextFieldHasPlaceholder()
            .displayedPasswordTextField()
            .passwordTextFieldHasPlaceholder()
            .displayedAgeTextField()
            .ageTextFieldHasPlaceholder()
    }

    @Test
    fun errorEmptyField(){
        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .displayedPasswordTextField()
            .displayedAgeTextField()
            .displayedButtonRegister()
            .clickRegisterButton()
            .displayedError()
            .errorEmpty()
    }

    @Test
    fun errorEmptyPassworAndAge(){

        val email = Faker().internet().emailAddress()

        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedPasswordTextField()
            .displayedAgeTextField()
            .displayedButtonRegister()
            .clickRegisterButton()
            .displayedError()
            .errorEmpty()
    }

    @Test
    fun errorEmptyAge(){

        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password()

        registerScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedPasswordTextField()
            .inputPassword(password)
            .displayedAgeTextField()
            .displayedButtonRegister()
            .clickRegisterButton()
            .displayedError()
            .errorEmpty()
    }

    @Test
    fun errorFormatEmail(){

        val email = Faker().internet().emailAddress().replace("@", "")
        val password = Faker().internet().password(6, 20)
        val age = Faker().random().nextInt(0, 99).toString()

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
            .errorEmail()
    }

    @Test
    fun errorMaxLenPassword(){

        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(21,22)
        val age = Faker().random().nextInt(0, 99).toString()

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
            .errorPassword()
    }

    @Test
    fun errorMinAge() {

        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6, 20)
        val age: String = (-1).toString()

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
            .errorAge()
    }

    @Test
    fun errorMaxAge() {

        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6,20)
        val age: String = 100.toString()

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
            .errorAge()
    }

}