package ru.yavshok.app.Screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
class ProfileScreenPage(private val rule: ComposeTestRule) {

    private val nameTag = Tags.ProfileScreen.userName
    private val statusTag = Tags.ProfileScreen.userStatus
    private val editButtonTag = Tags.ProfileScreen.buttonEdit
    private val logoutButtonTag = Tags.ProfileScreen.buttonLogOut
    private val header = Tags.ProfileScreen.header


    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 15_000L,
            matcher = hasTestTag(header)
        )
    }

    fun assertIsDisplayedUserName() = apply {
        rule.onNodeWithTag(nameTag).assertIsDisplayed()
    }

    fun assertIsDisplayedUserStatus() = apply {
        rule.onNodeWithTag(statusTag).assertIsDisplayed()
    }

    fun assertStatusTextContains(expected: String) = apply {
        rule.onNodeWithTag(statusTag).assertTextContains(expected)
    }

    fun assertIsDisplayedButtonEdit() = apply {
        rule.onNodeWithTag(editButtonTag).assertIsDisplayed()
    }

    fun clickEditButton() = apply {
        rule.onNodeWithTag(editButtonTag).performClick()
    }

    fun assertIsDisplayedLogout() = apply {
        rule.onNodeWithTag(logoutButtonTag).assertIsDisplayed()
    }

    fun clickLogoutButton() = apply {
        rule.onNodeWithTag(logoutButtonTag).performClick()
    }

    fun nameLineHasName(name: String) = apply {
        rule.onNodeWithTag(nameTag).assertTextContains(name)
    }
    fun assertUserNameNotDisplayed() = apply {
        rule.onNodeWithTag(Tags.ProfileScreen.userName).assertDoesNotExist()
    }
}
