package ru.yavshok.app
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.LoginPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import com.github.javafaker.Faker
import ru.yavshok.app.dataEmail

class LoginPageTests {
    @get: Rule
    val composeRule = createComposeRule()
    val Factory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    lateinit var page : LoginPage
    @Before
    fun setup(){
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = Factory)
            )
        }
        page = LoginPage(composeRule)
    }

    @Test
    fun shouldShowErrorWithEmptyFields(){
        with(page){
            isVisibleTitle()
            isVisibleLoginButton()
            clickLogin()
            isVisibleFieldError()
            errorIs("Заполните все поля")
        }
    }

    @Test
    fun shouldShowErrorWithNotValidEmail(){
        val nonValEmail = Faker().letterify("???????")
        val password = Faker().internet().password()
        with(page){
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            fillEmail(nonValEmail)
            fillPassword(password)
            isVisibleLoginButton()
            clickLogin()
            isVisibleFieldError()
            errorIs("Неверный формат email")
        }
    }

    @Test
    fun shouldShowErrorWithNotExistEmail(){
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password()
        with(page){
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            fillEmail(email)
            fillPassword(password)
            isVisibleLoginButton()
            clickLogin()
            isVisibleFieldError()
            errorIs("Неверный email или пароль")
        }
    }

    @Test
    fun shouldShowErrorWithWrongPassword(){
        val email = dataEmail
        val password = Faker().internet().password()
        with(page){
            isVisibleTitle()
            isVisibleLoginButton()
            fillEmail(email)
            fillPassword(password)
            clickLogin()
            isVisibleFieldError()
            errorIs("Неверный email или пароль")
        }
    }
}