package ru.yavshok.app.ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    // Tags.MainScreen.screenTitle
    // Tags.MainScreen.loginButton
    // Tags.LoginScreen.screenTitle

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 50_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()

    }

}