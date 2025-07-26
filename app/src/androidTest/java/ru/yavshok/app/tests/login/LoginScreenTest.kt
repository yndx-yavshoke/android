package ru.yavshok.app.tests.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private lateinit var loginPage: LoginPage

    @Before
    fun setUp(){
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        loginPage = LoginPage(composeRule)
        loginPage.allElementsIsDisplayed()
    }

    @Test
    fun shouldShowErrorMessageWhenEmailEmpty() {
        val password = Faker().internet().password(6, 7)
        with(loginPage) {
            fillPassword(password)
            clickLoginButton()
            emptyFieldsErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenPasswordEmpty() {
        val email = Faker().internet().emailAddress( )
        with(loginPage) {
            fillEmail(email)
            clickLoginButton()
            emptyFieldsErrorMessageIsDisplayed()
        }
    }
}