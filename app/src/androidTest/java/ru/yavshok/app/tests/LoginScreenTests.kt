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
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {
    @get: Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Test
    fun shouldNotAuthorizeUnregisteredUser() = fixture(composeRule) {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        loginPage.title.assertIsDisplayed()
        loginPage.shouldNotAuthorizeUnregisteredUser()
    }

    @Test
    fun shouldNotAuthorizeWrongPassword() = fixture(composeRule) {

        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        loginPage.title.assertIsDisplayed()
        loginPage.shouldNotAuthorizeWrongPassword()
    }
}