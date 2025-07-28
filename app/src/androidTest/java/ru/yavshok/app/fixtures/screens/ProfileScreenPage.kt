package ru.yavshok.app.fixtures.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreenPage (private val composeRule: ComposeTestRule) {
    private val avatar = composeRule.onNodeWithTag(Tags.ProfileScreen.avatarContainer)
    private val userName = composeRule.onNodeWithTag(Tags.ProfileScreen.userName)
    private val ageStatusText = composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText)
    private val logoutButton = composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton, useUnmergedTree = true)
    private val editButton = composeRule.onNodeWithTag(Tags.ProfileScreen.editButton, useUnmergedTree = true)

    fun checkAvatarIsDisplayed() : ProfileScreenPage {
        avatar.assertIsDisplayed()
        return this
    }

    fun checkUserNameText(expectedText: String): ProfileScreenPage {
        userName.assertIsDisplayed()
        userName.assertTextEquals(expectedText)
        return this
    }

    fun checkAgeStatusText(expectedText: String): ProfileScreenPage {
        ageStatusText.assertIsDisplayed()
        ageStatusText.assertTextEquals(expectedText)
        return this
    }

    fun checkLogoutButtonIsDisplayed(): ProfileScreenPage {
        logoutButton.assertIsDisplayed()
        return this
    }

    fun checkEditButtonIsDisplayed(): ProfileScreenPage {
        editButton.assertIsDisplayed()
        return this
    }

    fun checkEditButtonTextIsDisplayed(expected: String = "Change name"): ProfileScreenPage {
        editButton.assertTextContains(expected)
        return this
    }

    fun clickLogout(): ProfileScreenPage {
        logoutButton.performClick()
        return this
    }

    fun clickEdit(): ProfileScreenPage {
        editButton.assertIsDisplayed().assertHasClickAction()
        editButton.performClick()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitUserName(): ProfileScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfileScreen.userName),
            timeoutMillis = 5_000L
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitEditButton(): ProfileScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfileScreen.editButton),
            timeoutMillis = 5_000L
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitEditButtonIsClickable(): ProfileScreenPage {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            try {
                composeRule.onNodeWithTag(Tags.ProfileScreen.editButton)
                    .assertIsDisplayed()
                    .assertHasClickAction()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        return this
    }

}