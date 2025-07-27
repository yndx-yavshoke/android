package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import  ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import org.junit.Test
import org.junit.Rule
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    // Tags.MainScreen.screenTitle
    // Tags.MainScreen.emailTextField
    @get:Rule
    val composeRule = createComposeRule()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Test
    fun shouldReactOnUnregisteredEmail() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("notpupu@gmail.com")
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("notpupu@gmail.com")
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.errorMessage)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.errorMessage).assertIsDisplayed()
    }
    @Test
    fun checkButtonIsNotClickableBeforeInput() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.checkButton).assertIsNotEnabled()
    }
}


