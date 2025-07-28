package ru.yavshok.app.pages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreenPage (private val composeRule: ComposeTestRule) {
    public val profileImage = composeRule.onNodeWithTag("profile_screen.profile_image")
    public val profileUserName = composeRule.onNodeWithTag("profile_screen.profile_user_name")
    public val profileAgeStatus = composeRule.onNodeWithTag("profile_screen.profile_age_status")
    public val logoutButton = composeRule.onNodeWithTag("profile_screen.profile_logout_button")
    public val editButton = composeRule.onNodeWithTag("profile_screen.profile_edit_button")

    fun clickEditButton(): ProfileScreenPage {
        editButton.assertIsDisplayed()
        editButton.performClick()
        return this
    }

    fun clickLogoutButton(): ProfileScreenPage {
        logoutButton.assertIsDisplayed()
        logoutButton.performClick()
        return this
    }
}