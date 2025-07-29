package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ProfileScreen(private val rule: ComposeTestRule) {
    private val avatar get() = rule.onNodeWithTag(Tags.ProfileScreen.avatar)
    private val nameProfile get() = rule.onNodeWithTag(Tags.ProfileScreen.nameProfile)
    private val statusProfile get() = rule.onNodeWithTag(Tags.ProfileScreen.statusProfile)
    private val logOutButton get() = rule.onNodeWithTag(Tags.ProfileScreen.logOutButton)
    private val editProfileButton get() = rule.onNodeWithTag(Tags.ProfileScreen.editProfileButton)

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.ProfileScreen.nameProfile)
        )
    }

    fun assertScreenDisplayed() {
//        avatar.assertIsDisplayed()
//        nameProfile.assertIsDisplayed()
//        statusProfile.assertIsDisplayed()
//        logOutButton.assertIsDisplayed()
        editProfileButton.assertIsDisplayed()
    }

    fun clickOnLogOut() {
        logOutButton.performClick()
    }

    fun clickOnEditProfile() {
        editProfileButton.performClick()
    }

    fun assertNameEqualChangedName(name: String) {
        nameProfile.assertTextEquals(name)
    }
}