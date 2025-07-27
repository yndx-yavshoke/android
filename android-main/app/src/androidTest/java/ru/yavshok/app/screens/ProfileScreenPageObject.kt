package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreenPageObject(private val testRule: ComposeTestRule) {
    private val catName = testRule.onNodeWithTag(Tags.ProfileScreen.catName)
    private val catAge = testRule.onNodeWithTag(Tags.ProfileScreen.catAge)
    private val logoutButton = testRule.onNodeWithTag(Tags.ProfileScreen.logoutButton)
    private val editButton = testRule.onNodeWithTag(Tags.ProfileScreen.editButton)

    fun clickLogout(): ProfileScreenPageObject {
        logoutButton.assertIsDisplayed()
        logoutButton.performClick()
        return this
    }

    fun clickEdit(): ProfileScreenPageObject {
        editButton.assertIsDisplayed()
        editButton.performClick()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitCatName(): ProfileScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.ProfileScreen.catName),
            5000
        )
        return this
    }

    fun assertLogoutButton(): ProfileScreenPageObject {
        logoutButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Logout")
        return this
    }

    fun assertEditButton(): ProfileScreenPageObject {
        editButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Edit Profile")
        return this
    }

    fun assertCatAge(): ProfileScreenPageObject {
        catAge
            .assertIsDisplayed()
            .assertTextContains("котик")
        return this
    }

    fun assertCatName(name: String): ProfileScreenPageObject {
        catName
            .assertIsDisplayed()
            .assertTextContains(name)
        return this
    }

}