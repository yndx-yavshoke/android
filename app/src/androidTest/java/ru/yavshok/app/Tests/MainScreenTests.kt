package ru.yavshok.app.Tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Screens.MainScreenPage
import ru.yavshok.app.Utils.DataGenerator
import ru.yavshok.app.ui.screens.MainScreen


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mainScreen: MainScreenPage

    @Before
    fun setUp() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }
        mainScreen = MainScreenPage(composeRule)
    }

    @Test
    fun shouldDisplayAllMainScreenFields() {
        with(mainScreen) {
            assertTitleIsDisplayed()
            assertEmailFieldIsDisplayed()
            assertCheckLoginButtonIsDisplayed()
            assertLoginButtonIsDisplayed()
        }
    }

    @Test
    fun shouldShowSuccessEmailCheck() {
        with(mainScreen) {
            val validEmail = DataGenerator.TestUser.existingUserEmail
            enterEmail(validEmail)
            clickCheckLoginButton()
            assertSuccessEmailCheck()
        }
    }

    @Test
    fun shouldShowFailedEmailCheck() {
        with(mainScreen) {
            val failedEmail = DataGenerator.FakeUser.email()
            enterEmail(failedEmail)
            clickCheckLoginButton()
            assertFailedEmailCheck()
        }
    }

    @Test
    fun shouldDisableCheckButtonWithInvalidEmail() {
        with(mainScreen) {
            enterEmail("invalid")
            assertCheckLoginButtonIsDisabled()
        }
    }

    @Test
    fun shouldEnableCheckButtonWithValidEmail() {
        with(mainScreen) {
            val validEmail = DataGenerator.TestUser.existingUserEmail
            enterEmail(validEmail)
            assertCheckLoginButtonIsEnabled()
        }
    }


    @Test
    fun shouldClearEmailAndKeepFieldVisible() {
        with(mainScreen) {
            val email = DataGenerator.FakeUser.email()
            enterEmail(email)
            assertEmailFieldHasText(email)
            clearEmailField()
            assertEmailFieldIsDisplayed()
        }
    }

    @Test
    fun shouldClickLoginButton() {
        with(mainScreen) {
            assertLoginButtonIsDisplayed()
            clickLoginButton()
        }
    }
}
