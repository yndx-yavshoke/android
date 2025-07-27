package ru.yavshok.app.pages

import android.R
import android.R.string
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import ru.yavshok.app.Tags

class ProfileScreenPage(private val composeRule: ComposeTestRule) {

    private val profileImage = Tags.ProfileScreen.profileImage
    private val profileTextName = Tags.ProfileScreen.profileTextName
    private val profileTextStatus = Tags.ProfileScreen.profileTextStatus
    private val exitButton = Tags.ProfileScreen.exitButton
    private val editProfileButton = Tags.ProfileScreen.editProfileButton

    fun displayProfileImage() = apply {
        composeRule.onNodeWithTag(profileImage).assertIsDisplayed()
    }

    fun displayProfileTextName() = apply {
        composeRule.onNodeWithTag(profileTextName).assertIsDisplayed()
    }

    fun displayProfileTextStatus() = apply {
        composeRule.onNodeWithTag(profileTextStatus).assertIsDisplayed()
    }

    fun displayExitButton() = apply {
        composeRule.onNodeWithTag(exitButton).assertIsDisplayed()
    }

    fun displayEditProfileButton() = apply {
        composeRule.onNodeWithTag(editProfileButton).assertIsDisplayed()
    }

    fun clickExitButton() = apply {
        composeRule.onNodeWithTag(exitButton).performClick()
    }

    fun clickEditProfileButton() = apply {
        composeRule.onNodeWithTag(editProfileButton).performClick()
    }
}