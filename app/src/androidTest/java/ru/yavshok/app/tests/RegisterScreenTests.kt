package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.screens.RegisterScreen
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import utils.User

class RegisterScreenTests {
    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var registerScreen: RegisterScreen
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    val user = User.generateRandomUser()

    @Before
    fun setup() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        registerScreen = RegisterScreen(composeRule)
        registerScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldShowAnErrorWithEmptyFieldsOnRegisterScreen() {
        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsEmptyFields()
    }

    @Test
    fun shouldShowAnErrorWithIncorrectEmail() {
        registerScreen.typeEmail("incorrect email")
        registerScreen.typePassword(user.password)
        registerScreen.typeAge(user.age)

        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsIncorrectEmail()
    }

    @Test
    fun shouldShowAnErrorWithShortPassword() {
        registerScreen.typeEmail(user.email)
        registerScreen.typePassword("0")
        registerScreen.typeAge(user.age)

        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsIncorrectPassword()
    }

    @Test
    fun shouldShowAnErrorWithLongPassword() {
        registerScreen.typeEmail(user.email)
        registerScreen.typePassword("1".repeat(21))
        registerScreen.typeAge(user.age)

        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsIncorrectPassword()
    }

    @Test
    fun shouldShowAnErrorWithAgeEqualNegativeNumber() {
        registerScreen.typeEmail(user.email)
        registerScreen.typePassword(user.password)
        registerScreen.typeAge("-1")

        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsIncorrectAge()
    }

    @Test
    fun shouldShowAnErrorWithAgeEqualLetter() {
        registerScreen.typeEmail(user.email)
        registerScreen.typePassword(user.password)
        registerScreen.typeAge("A")

        registerScreen.clickOnRegister()
        registerScreen.assertErrorIsIncorrectAge()
    }
}