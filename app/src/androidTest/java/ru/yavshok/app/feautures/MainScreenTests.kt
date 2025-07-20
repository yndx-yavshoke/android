package ru.yavshok.app.feautures

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.MainScreenPageObject
import ru.yavshok.app.ui.screens.MainScreen


@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<ComponentActivity>()

    private lateinit var mainScreen: MainScreenPageObject

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }
        mainScreen = MainScreenPageObject(composeTestRule)
    }

    @Test
    fun emailShouldExist() {
        mainScreen.checkEmail("example@mail.ru")
        composeTestRule.waitUntil(5000) {
            try {
                mainScreen.success.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        mainScreen.fail.assertDoesNotExist()
        }

    @Test
    fun emailShouldNotExist() {
        mainScreen.checkEmail("not_exist_email@example.ru")
        composeTestRule.waitUntil(5000) {
            try {
                mainScreen.fail.assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        mainScreen.success.assertDoesNotExist()
    }

    @Test
    fun checkAllElements() {
        mainScreen.title.assertIsDisplayed()
        mainScreen.emailField.assertIsDisplayed()
        mainScreen.emailField.assertTextContains("Введите Email")
        mainScreen.checkButton.assertIsNotEnabled()
        mainScreen.enterEmail("random_email@mail.ru")
        mainScreen.checkButton.assertIsEnabled()
        mainScreen.checkButton.assertTextContains("Я в шоке?")
        mainScreen.toLoginButton.assertIsDisplayed()
        mainScreen.toLoginButton.assertTextContains("В шок")
    }
}