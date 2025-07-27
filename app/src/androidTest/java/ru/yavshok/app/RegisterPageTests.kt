package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.RegisterPage
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.dataEmail

class RegisterPageTests {
    @get: Rule
    val composeRule = createComposeRule()
    val Factory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    lateinit var page : RegisterPage
    @Before
    fun setup(){
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = Factory)
            )
        }
        page = RegisterPage(composeRule)
    }


    @Test
    fun shouldShowErrorWithNoEmailRegistration(){
        val password = Faker().internet().password()
        val age = Faker().number().toString()
        with(page){
            isVisibleTitle()
            isVisiblePasswordTextField()
            isVisibleAgeTextField()
            fillPassword(password)
            fillAge(age)
            isVisibleRegisterButton()
            clickRegister()
            isVisibleFieldError()
            errorIs("Заполните все поля")
        }
    }


    @Test
    fun shouldShowErrorWithNoPasswordRegistration(){
        val email = Faker().internet().emailAddress()
        val age = Faker().number().toString()
        with(page){
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisibleAgeTextField()
            fillEmail(email)
            fillAge(age)
            isVisibleRegisterButton()
            clickRegister()
            isVisibleFieldError()
            errorIs("Заполните все поля")
        }
    }


    @Test
    fun shouldShowErrorWithNoAgeRegistration(){
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password()
        with(page){
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            fillEmail(email)
            fillPassword(password)
            isVisibleRegisterButton()
            clickRegister()
            isVisibleFieldError()
            errorIs("Заполните все поля")
        }
    }

    @Test
    fun shouldShowErrorWithExistUserEmail(){
        val email = dataEmail
        val password = Faker().internet().password()
        val age = Faker().number().toString()
        with(page){
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            isVisibleAgeTextField()
            fillEmail(email)
            fillPassword(password)
            fillAge(age)
            isVisibleRegisterButton()
            clickRegister()
            isVisibleFieldError()
            errorIs("Пользователь с таким email же существует")
        }
    }
}