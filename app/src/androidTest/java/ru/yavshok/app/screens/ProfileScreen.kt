package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ProfileScreen(private val composeRule: ComposeTestRule) {

    private val editProfileButton = Tags.ProfileScreen.editProfileButton
    private val logoutButton = Tags.ProfileScreen.logoutButton
    private val nameLabel = Tags.ProfileScreen.nameLabel
    private val statusLabel = Tags.ProfileScreen.statusLabel

    fun waitToLoad() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(editProfileButton),
            timeoutMillis = 5_000L
        )
    }

    fun checkEditProfileButtonIsDisplayed() {
        composeRule.onNodeWithTag(editProfileButton).assertIsDisplayed()
    }

    fun checkLogoutButtonIsDisplayed() {
        composeRule.onNodeWithTag(logoutButton).assertIsDisplayed()
    }

    fun checkNameLabelIsDisplayed() {
        composeRule.onNodeWithTag(nameLabel).assertIsDisplayed()
    }

    fun checkStatusLabelIsDisplayed() {
        composeRule.onNodeWithTag(statusLabel).assertIsDisplayed()
    }

    fun clickLogoutButton()  {
        composeRule.onNodeWithTag(logoutButton).performClick()
    }

    fun clickEditProfileButton()  {
        composeRule.onNodeWithTag(editProfileButton).performClick()
    }
}