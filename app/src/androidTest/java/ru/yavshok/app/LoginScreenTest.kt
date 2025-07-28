package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.screens.MainScreen
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory
//import com.github.javafaker.Faker

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var loginScreen: LoginScreen
    private lateinit var mainScreen: MainScreen

    val vmFactory = ViewModelFactory(getApplicationContext())

    @Before
    fun setUp(){
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }

        loginScreen = LoginScreen(composeRule)
        mainScreen = MainScreen(composeRule)
    }

    @Test
    fun shouldDisplayedAllViewsOnLoginScreen() {
        with(loginScreen) {
            waitToLoad()
            checkScreenTitleIsDisplayed()
            checkEmailTextFieldIsDisplayed()
            checkPasswordTextFieldIsDisplayed()
            checkLoginButtonIsDisplayed()
            checkBackButtonIsDisplayed()
            checkRegisterButtonIsDisplayed()
        }
    }

    @Test
    fun shouldTypeEmailOnLoginScreen(){
        val emailName = "my@yandex.ru"
        with(loginScreen) {
            waitToLoad()
            checkEmailTextFieldIsDisplayed()
            enterEmail(emailName)
            assertEmailDisplayed(emailName)
        }
    }
}