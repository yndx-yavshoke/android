package ru.yavshok.app.fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreen (private val rule: ComposeTestRule) {

    fun assertUserNameIsDisplayed(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.userName).assertIsDisplayed()
        return this
    }

    fun assertUserStatusIsDisplayed(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.userStatus).assertIsDisplayed()
        return this
    }

    fun assertUserStatusExists(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.userStatus).assertExists()
        return this
    }

    fun assertEditProfileButtonIsDisplayed(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
        return this
    }
    fun assertEditProfileButtonExists(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertExists()
        return this
    }

    fun assertLogoutButtonExists(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertExists()
        return this
    }

    fun assertLogoutButtonIsDisplayed(): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertIsDisplayed()
        return this
    }

    fun assertLogoutButtonDoesNotExist(): ProfileScreen{
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertDoesNotExist()
        return this
    }

    fun clickEditProfileButton():  ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
        return this
    }

    fun clickLogoutButton():  ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()
        return this
    }

    fun assertNameContainsText(name:String): ProfileScreen {
        rule.onNodeWithTag(Tags.ProfileScreen.userName).assertTextContains(name)
        return this
    }



}