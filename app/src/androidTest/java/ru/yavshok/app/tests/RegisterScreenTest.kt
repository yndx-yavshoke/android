package ru.yavshok.app.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory


data class User(
    val username: String,
    val password: String,
    val age: Int
)

@OptIn(ExperimentalTextApi::class)
@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {

    private val screenTitle = Tags.RegisterScreen.screenTitle
    private val emailTextField = Tags.RegisterScreen.emailTextField
    private val passwordTextField = Tags.RegisterScreen.passwordTextField
    private val ageTextField = Tags.RegisterScreen.ageTextField
    private val registerButton = Tags.RegisterScreen.registerButton
    private val backButton = Tags.RegisterScreen.backButton
    private val errorMessage = Tags.RegisterScreen.errorMessage
    private val errorTextMessage = "Неверный формат email"
    private val errorEmptyTextMessage = "Заполните все поля"

    private val testUser = User("invalid@ru", "qwerty", 30)

    @get:Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }

    @Test
    fun shouldDisplayAllElementsOnRegisterScreen() {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(ageTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(backButton).assertIsDisplayed()
    }

    @Test
    fun shouldDisplayErrorEmptyInputs() {
        composeRule.onNodeWithTag(registerButton).performClick()
        composeRule.onNodeWithTag(errorMessage).assertTextContains(errorEmptyTextMessage)
    }

    @Test
    fun shouldDisplayErrorTypeOnlyEmail() {
        val user1 = User("invalid@ru", "qwerty", 30)
        composeRule.onNodeWithTag(emailTextField).performTextInput(user1.username)
        composeRule.onNodeWithTag(registerButton).performClick()
        composeRule.onNodeWithTag(errorMessage).assertTextContains(errorEmptyTextMessage)
    }

    @Test
    fun shouldDisplayErrorTypeOnlyPassword() {
        composeRule.onNodeWithTag(passwordTextField).performTextInput(testUser.password)
        composeRule.onNodeWithTag(registerButton).performClick()
        composeRule.onNodeWithTag(errorMessage).assertTextContains(errorEmptyTextMessage)
    }

    @Test
    fun shouldDisplayErrorTypeOnlyAge() {
        composeRule.onNodeWithTag(ageTextField).performTextInput(testUser.age.toString())
        composeRule.onNodeWithTag(registerButton).performClick()
        composeRule.onNodeWithTag(errorMessage).assertTextContains(errorEmptyTextMessage)
    }

    @Test
    fun shouldDisplayErrorInvalidFormat() {
        composeRule.onNodeWithTag(emailTextField).performTextInput(testUser.username)
        composeRule.onNodeWithTag(passwordTextField).performTextInput(testUser.password)
        composeRule.onNodeWithTag(ageTextField).performTextInput(testUser.age.toString())
        composeRule.onNodeWithTag(registerButton).performClick()
        composeRule.onNodeWithTag(errorMessage).assertTextContains(errorTextMessage)
    }

}