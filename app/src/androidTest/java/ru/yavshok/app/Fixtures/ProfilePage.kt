package ru.yavshok.app.Fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfilePage (private val rule: ComposeTestRule) {

    fun assertUserNameIsDisplayed(): ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.userName).assertIsDisplayed()
        return this
    }

    fun assertUserStatusIsDisplayed(): ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.userStatus).assertIsDisplayed()
        return this
    }

    fun assertEditProfileButtonIsDisplayed(): ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.editProfileButton).assertIsDisplayed()
        return this
    }

    fun assertLogoutButtonIsDisplayed(): ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.logoutButton).assertIsDisplayed()
        return this
    }

    fun assertLogoutButtonDoesNotExist(): ProfilePage{
        rule.onNodeWithTag(Tags.ProfilePage.logoutButton).assertDoesNotExist()
        return this
    }

    fun clickEditProfileButton():  ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.editProfileButton).performClick()
        return this
    }

    fun clickLogoutButton():  ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.logoutButton).performClick()
        return this
    }

    fun assertNameContainsText(name:String): ProfilePage {
        rule.onNodeWithTag(Tags.ProfilePage.userName).assertTextContains(name)
        return this
    }



}