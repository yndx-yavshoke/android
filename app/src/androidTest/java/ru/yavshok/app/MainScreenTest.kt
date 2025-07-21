package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldBeVisibleAllTheElements() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.statusButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
    }

    @Test
    fun shouldBeReactionOnNonRegisterEmail() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.statusButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("abc@gmail.com")
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("abc@gmail.com")
        composeRule.onNodeWithTag(Tags.MainScreen.statusButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.nonRegisterText)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.nonRegisterText).assertIsDisplayed()
    }
    @Test
    fun checkStatusButtonIsNotClickableBeforeInput() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.statusButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.statusButton).assertIsNotEnabled()
    }
}