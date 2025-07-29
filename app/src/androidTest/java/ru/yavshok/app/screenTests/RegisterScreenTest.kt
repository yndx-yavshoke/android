package ru.yavshok.app.screenTests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.serpro69.kfaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.UserData
import ru.yavshok.app.data.model.User
import ru.yavshok.app.pages.RegisterScreenPage
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {

    @get: Rule
    val composeRule = createComposeRule()
    private lateinit var registerScreen: RegisterScreenPage
    private val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    private val faker = Faker()

    @Before
    fun setup() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        registerScreen = RegisterScreenPage(composeRule)
    }

    @Test
    fun registerWithAllEmptyFields() {
        registerScreen.title.assertIsDisplayed()
        registerScreen.clickRegisterButton()
        registerScreen.emptyFieldsError()
    }

    @Test
    fun registerWithEmailEmptyField() {
        val fakePassword = faker.person.hashCode().toString().take(10)
        val fakeAge = (10..99).random().toString()
        registerScreen.title.assertIsDisplayed()
        registerScreen.fillPasswordInput(fakePassword)
        registerScreen.fillAgeInput(fakeAge)
        registerScreen.clickRegisterButton()
        registerScreen.emptyFieldsError()
    }

    @Test
    fun registerWithPasswordEmptyField() {
        val fakeEmail = faker.internet.email()
        val fakeAge = (10..99).random().toString()
        registerScreen.title.assertIsDisplayed()
        registerScreen.fillEmailInput(fakeEmail)
        registerScreen.fillAgeInput(fakeAge)
        registerScreen.clickRegisterButton()
        registerScreen.emptyFieldsError()
    }

    @Test
    fun registerWithAgeEmptyField() {
        val fakeEmail = faker.internet.email()
        val fakePassword = faker.person.hashCode().toString().take(10)
        registerScreen.title.assertIsDisplayed()
        registerScreen.fillEmailInput(fakeEmail)
        registerScreen.fillPasswordInput(fakePassword)
        registerScreen.clickRegisterButton()
        registerScreen.emptyFieldsError()
    }

    @Test
    fun registerWithWrongEmailFormat() {
        val fakeEmail = faker.internet.email()
        val invalidEmail = fakeEmail.replace("@", "")
        val fakePassword = faker.person.hashCode().toString().take(10)
        val fakeAge = (10..99).random().toString()
        registerScreen.title.assertIsDisplayed()
        registerScreen.fullRegister(invalidEmail, fakePassword, fakeAge)
        registerScreen.clickRegisterButton()
        registerScreen.incorrectFormatOfEmailError()
    }

    @Test
    fun registerOfExistUser() {
        val fakeAge = (10..99).random().toString()
        registerScreen.title.assertIsDisplayed()
        registerScreen.fullRegister(UserData.TEST_EMAIL, UserData.TEST_PASSWORD, fakeAge)
        registerScreen.clickRegisterButton()
        @OptIn(androidx.compose.ui.test.ExperimentalTestApi::class)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.RegisterScreen.errorMessage)
        )
        registerScreen.existUserError()
    }

    @Test
    fun tooShortPassword() {
        val fakePassword = faker.person.hashCode().toString().take(4)
        val fakeAge = (10..99).random().toString()
        val fakeEmail = faker.internet.email()
        registerScreen.title.assertIsDisplayed()
        registerScreen.fullRegister(fakeEmail, fakePassword, fakeAge)
        registerScreen.clickRegisterButton()
        registerScreen.tooShortPasswordError()
    }
}