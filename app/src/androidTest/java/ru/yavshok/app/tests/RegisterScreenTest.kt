package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixture.fixture
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {

    @get: Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Test
    fun shouldNotRegisterAlreadyUsedEmail() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotRegisterAlreadyRegisteredUser()
    }

    @Test
    fun shouldNotRegisterVeryOld() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotRegisterTooOld()
    }

    @Test
    fun shouldNotRegisterUntilAtLeastOneFieldEmpty() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotRegisterEmptyAgeField()
    }

    @Test
    fun shouldNotAcceptSQL() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotAcceptSQL()
    }

    @Test
    fun shouldNotRegisterShortPassword() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotRegisterShortPassword()
    }

    @Test
    fun shouldNotRegisterLongPassword() = fixture(composeRule) {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        registerPage.title.assertIsDisplayed()
        registerPage.shouldNotRegisterLongPassword()
    }
}
