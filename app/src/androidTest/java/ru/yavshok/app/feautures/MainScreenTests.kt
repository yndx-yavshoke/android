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
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.FakerGenerator


@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<ComponentActivity>()

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }
        appPages = AppPages(composeTestRule)
    }

    @Test
    fun emailShouldExist() {
        appPages.mainScreen
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .success.assertIsDisplayed()
        appPages.mainScreen.fail.assertDoesNotExist()
        }

    @Test
    fun emailShouldNotExist() {
        appPages.mainScreen
            .checkEmail(FakerGenerator.generateRandomEmail())
            .waitFailMessage()
            .fail.assertIsDisplayed()
        appPages.mainScreen.success.assertDoesNotExist()
    }

    @Test
    fun checkAllElements() {
        appPages.mainScreen.title.assertIsDisplayed()
        appPages.mainScreen.emailField.assertIsDisplayed()
        appPages.mainScreen.emailField.assertTextContains("Введите Email")
        appPages.mainScreen.checkButton.assertIsNotEnabled()
        appPages.mainScreen.enterEmail("random_email@mail.ru")
        appPages.mainScreen.checkButton.assertIsEnabled()
        appPages.mainScreen.checkButton.assertTextContains("Я в шоке?")
        appPages.mainScreen.toLoginButton.assertIsDisplayed()
        appPages.mainScreen.toLoginButton.assertTextContains("В шок")
    }
}