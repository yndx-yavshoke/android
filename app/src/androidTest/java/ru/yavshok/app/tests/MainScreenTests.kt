package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.MainScreenFixture
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.utility.CredentialsData

@RunWith(AndroidJUnit4::class)

class MainScreenTests {

    @get: Rule
    val composeTestRule = createComposeRule()

    // Подключение
    private lateinit var mainScreen: MainScreenFixture

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }
        mainScreen = MainScreenFixture(composeTestRule)
    }

    // Тест: проверка всех элементов на экране
    @Test
    fun ElementsInScreen() {
        mainScreen.checkTitle()
        mainScreen.checkEmailTextField()
        mainScreen.checkLoginButton()
        mainScreen.checkCheckButton()
    }

    // Тест: проверка валидного пользователя на шоковость
    @Test
    fun shouldUserEmailExistInDatabase() {
        mainScreen.checkEmailTextField()
        mainScreen.enterEmailCredentials(CredentialsData.ValidUserData.email)
        Thread.sleep(3_000L)
        mainScreen.clickCheckButton()
        Thread.sleep(3_000L)
        mainScreen.checkSuccessMessage()
    }

    // Тест: проверка несуществующего пользователя на шоковость
    @Test
    fun shouldUserEmailNotExistInDatabase() {
        mainScreen.checkEmailTextField()
        mainScreen.enterEmailCredentials(CredentialsData.IncorrectUserData.email)
        Thread.sleep(3_000L)
        mainScreen.clickLoginButton()
        Thread.sleep(3_000L)
        mainScreen.checkFailedMessage()
        // ono flakaet ya shas vskrous' 8h bez pereriva
    }


}