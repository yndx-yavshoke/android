package ru.yavshok.app.Tests.LoginScreenTests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Screens.LoginScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    private lateinit var loginScreen: LoginScreenPage

    @Before
    fun setUp(){
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        loginScreen = LoginScreenPage(composeRule)
    }

    @Test
    fun allViewDisplayeInLoginScreen() {
        loginScreen
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .displayedPasswordTextField()
            .displayedButtonInShock()
            .displayedButtonBack()
            .displayedButtonRegister()
    }

    @Test
    fun placeholdersInLoginScreen(){
        loginScreen
            .displayedEmailTextField()
            .emailTextFieldHasPlaceholder()
            .displayedPasswordTextField()
            .passwordTextFieldHasPlaceholder()
    }

    @Test
    fun emptyInput(){
        loginScreen
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .displayedButtonInShock()
            .clickButtonInShock()
            .displayedError()
            .errorEmpty()
    }

    @Test
    fun emptyPassword(){

        val email = Faker().internet().emailAddress()

        loginScreen
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .inputEmail(email)
            .displayedButtonInShock()
            .clickButtonInShock()
            .displayedError()
            .errorEmpty()
    }

    @Test
    fun emptyEmail(){

        val password = Faker().internet().password()

        loginScreen
            .displayedTitleLoginScreen()
            .displayedEmailTextField()
            .inputPassword(password)
            .displayedButtonInShock()
            .clickButtonInShock()
            .displayedError()
            .errorEmpty()
    }
}