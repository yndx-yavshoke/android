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
import ru.yavshok.app.pages.RegisterPage
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.ui.screens.register.RegisterScreen
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    private lateinit var registerPage: RegisterPage

    @Before
    fun setUp(){
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        registerPage = RegisterPage(composeRule)
        registerPage.allElementsIsDisplayed()
    }

    @Test
    fun shouldShowErrorMessageWhenEmailInvalidFormat() {
        val email = Faker().internet().emailAddress().replace('@', '.')
        val password = Faker().internet().password(6, 7)
        val age = Random.nextInt(1, 100)

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidEmailErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenEmailEmpty() {
        val password = Faker().internet().password(6, 7)
        val age = Random.nextInt(1, 100)

        with(registerPage) {
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            emptyFieldsErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenPasswordEmpty() {
        val email = Faker().internet().emailAddress()
        val age = Random.nextInt(1, 100)

        with(registerPage) {
            fillEmail(email)
            fillAge(age.toString())
            clickRegisterButton()

            emptyFieldsErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenAgeEmpty() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6, 7)

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            clickRegisterButton()

            emptyFieldsErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenAgeDecimal() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6, 7)
        val age = 2.2

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidAgeErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenAgeGreater99() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6, 7)
        val age = 100

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidAgeErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenAgeNegative() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(6, 7)
        val age = -1

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidAgeErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenPasswordShorter5() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(4, 5)
        val age = Random.nextInt(1, 100)

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidPasswordErrorMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorMessageWhenPasswordGreater20() {
        val email = Faker().internet().emailAddress()
        val password = Faker().internet().password(21, 22)
        val age = Random.nextInt(1, 100)

        with(registerPage) {
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            clickRegisterButton()

            invalidPasswordErrorMessageIsDisplayed()
        }
    }
}