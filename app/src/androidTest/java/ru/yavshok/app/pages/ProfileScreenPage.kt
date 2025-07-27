package ru.yavshok.app.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags

class ProfileScreenPage(private val rule: ComposeTestRule) {

    fun assertUserNameIsDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.userName).assertIsDisplayed()
    }

    fun assertUserStatusIsDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.userStatus).assertIsDisplayed()
    }

    fun assertUserAvatarIsDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.userAvatar).assertIsDisplayed()
    }

    fun assertLogoutButtonIsDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertIsDisplayed()
    }

    fun assertEditButtonIsDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.editButton).assertIsDisplayed()
    }

    fun clickLogoutButton() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()
    }

    fun clickEditButton() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.editButton).performClick()
    }

    fun isUserNameDisplayed(): Boolean {
        return rule.onAllNodesWithTag(Tags.ProfileScreen.userName)
            .fetchSemanticsNodes()
            .isNotEmpty()
    }

    fun assertUserAvatarDisplayed(): ProfileScreenPage {
        rule.onNodeWithTag(Tags.ProfileScreen.userAvatar)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertUserNameDisplayed(): ProfileScreenPage {
        rule.onNodeWithTag(Tags.ProfileScreen.userName)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertUserStatusDisplayed(): ProfileScreenPage {
        rule.onNodeWithTag(Tags.ProfileScreen.userStatus)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertEditButtonDisplayed(): ProfileScreenPage {
        rule.onNodeWithTag(Tags.ProfileScreen.editButton)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertLogoutButtonDisplayed(): ProfileScreenPage {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton)
            .assertExists()
            .assertIsDisplayed()
        return this
    }
}
