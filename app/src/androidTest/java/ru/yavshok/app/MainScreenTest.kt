package ru.yavshok.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import com.github.javafaker.Faker

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get: Rule
    val composeRule = createComposeRule()

    private fun setUpContent() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }

    class Faker {
        private val faker = Faker()
        val fakeEmail = faker.internet().emailAddress()
    }

    @Test
    fun shouldCheckMainScreenElements() {
        setUpContent()

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
    }

    @Test
    fun shouldCheckEmailExist() {
        setUpContent()

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("test@yavshok.ru")
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.emailExistTrue).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(Tags.MainScreen.emailExistTrue).assertIsDisplayed()
    }

    @Test
    fun shouldCheckEmailNotExist() {
        setUpContent()

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(fakeEmail)
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.emailExistFalse).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag(Tags.MainScreen.emailExistFalse).assertIsDisplayed()
    }
    
}
