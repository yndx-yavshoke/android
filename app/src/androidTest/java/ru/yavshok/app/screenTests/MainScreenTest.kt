package ru.yavshok.app.screenTests

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.pages.exists
import ru.yavshok.app.ui.screens.MainScreen
import io.github.serpro69.kfaker.Faker


@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var mainScreen: MainScreenPage
    private val faker = Faker()

    @Before
    fun setup() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }
        mainScreen = MainScreenPage(composeRule)
    }

    @Test
    fun userNotExist() {
        val fakeEmail = faker.internet.email()
        mainScreen.fillEmailInput(fakeEmail)
        mainScreen.clickCheckButton()

        composeRule.waitUntil(5000) {
            mainScreen.errorText.exists()
        }

        mainScreen.errorText.assertIsDisplayed()
        mainScreen.successText.assertDoesNotExist()
    }

    @Test
    fun userExist() {
        mainScreen.fillEmailInput("hi@mail.com")
        mainScreen.clickCheckButton()

        composeRule.waitUntil(5000) {
            mainScreen.successText.exists()
        }

        mainScreen.successText.assertIsDisplayed()
        mainScreen.errorText.assertDoesNotExist()
    }
}