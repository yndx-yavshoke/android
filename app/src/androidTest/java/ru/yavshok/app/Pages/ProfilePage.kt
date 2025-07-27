package ru.yavshok.app.Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ProfileScreenPage(private val rule: ComposeTestRule) {

    private val userAvatar = Tags.ProfileScreen.userAvatar
    private val userName = Tags.ProfileScreen.userName
    private val userStatus = Tags.ProfileScreen.userStatus
    private val buttonEditProfile = Tags.ProfileScreen.buttonEdit
    private val buttonLogout = Tags.ProfileScreen.buttonLogOut

    fun displayedUserAvatar() = apply {
        rule.onNodeWithTag(userAvatar).assertIsDisplayed()
    }

    fun displayedUserName() = apply {
        rule.onNodeWithTag(userName).assertIsDisplayed()
    }

    fun displayedUserStatus() = apply {
        rule.onNodeWithTag(userStatus).assertIsDisplayed()
    }

    fun displayedLogout() = apply {
        rule.onNodeWithTag(buttonLogout).assertIsDisplayed()
    }

    fun displayedButtonEdit() = apply {
        rule.onNodeWithTag(buttonEditProfile).assertIsDisplayed()
    }

    fun existUserName() = apply {
        rule.onNodeWithTag(userName).assertExists()
    }

    fun clickEditButton() = apply {
        rule.onNodeWithTag(buttonEditProfile).performClick()
    }

    fun clickLogoutButton() = apply {
        rule.onNodeWithTag(buttonLogout).performClick()
    }

    fun nameLineHasName(name: String) = apply {
        rule.onNodeWithTag(userName).assertTextContains(name)
    }

    fun timeoutOnProfile() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 20_000L,
            matcher = hasTestTag(userAvatar)
        )
    }
}