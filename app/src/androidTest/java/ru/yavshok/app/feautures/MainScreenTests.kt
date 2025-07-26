package ru.yavshok.app.feautures

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.MainScreenPageObject
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.FakerGenerator


@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get: Rule
    val composeTestRule  = createComposeRule()

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
        mainScreen
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .assertSuccessMessage()
        }

    @Test
    fun emailShouldNotExist() {
        mainScreen
            .checkEmail(FakerGenerator.generateRandomEmail())
            .waitFailMessage()
            .assertFailMessage()
    }

    @Test
    fun checkAllElements() {
        mainScreen
            .assertTitle()
            .assertEmailField()
            .assertCheckButtonIsNotEnabled()
            .assertLoginButton()
    }

    @Test
    fun checkButton() {
        mainScreen
            .assertCheckButtonIsNotEnabled()
            .enterEmail(Datas.User.email)
            .assertCheckButtonIsEnabled()
    }
}