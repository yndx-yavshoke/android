package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ProfileScreen(private val composeRule: ComposeTestRule) {

    private val editProfileButton = Tags.ProfileScreen.editProfileButton

    fun waitToLoad() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(editProfileButton),
            timeoutMillis = 5_000L
        )
    }

    fun verifyEditButtonIsDisplayed() {
        composeRule.onNodeWithTag(editProfileButton).assertIsDisplayed()
    }
}