package ru.yavshok.app.screenTests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import kotlin.concurrent.thread
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onAllNodesWithTag


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get: Rule
    val composeRule = createComposeRule()
    private  lateinit var loginScreen: LoginScreenPage
    private val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
    private val faker = Faker()

    @Before
    fun setup() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
        loginScreen = LoginScreenPage(composeRule)
    }

    @Test
    fun loginWithAllEmptyFields() {
        loginScreen.title.assertIsDisplayed()
        loginScreen.clickLoginButton()
        loginScreen.emptyFieldsError()
    }

    @Test
    fun loginWithEmptyEmail() {
        val fakePassword = faker.person.hashCode().toString().take(10)
        loginScreen.title.assertIsDisplayed()
        loginScreen.fillPasswordInput(fakePassword)
        loginScreen.emailInput.assertTextContains("")
        loginScreen.clickLoginButton()
        loginScreen.emptyFieldsError()
    }

    @Test
    fun loginWithEmptyPassword() {
        val fakeEmail = faker.internet.email()
        loginScreen.title.assertIsDisplayed()
        loginScreen.fillEmailInput(fakeEmail)
        loginScreen.passwordInput.assertTextContains("")
        loginScreen.clickLoginButton()
        loginScreen.emptyFieldsError()
    }

    @Test
    fun loginWithWrongData() {
        val fakePassword = faker.person.hashCode().toString().take(10)
        loginScreen.title.assertIsDisplayed()
        loginScreen.fullLogin(UserData.TEST_EMAIL, fakePassword)
        @OptIn(androidx.compose.ui.test.ExperimentalTestApi::class)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.LoginScreen.errorMessage)
        )
        loginScreen.incorrectPasswordOrEmailError()
    }


}