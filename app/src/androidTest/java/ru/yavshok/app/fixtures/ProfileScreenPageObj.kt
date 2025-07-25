package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ProfileScreenPageObj(private val rule: ComposeContentTestRule) {

    private val editButton = Tags.ProfileScreen.editButton
    private val logoutButton = Tags.ProfileScreen.logoutButton


    fun waitForScreen(): ProfileScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(logoutButton)
        )
        return this
    }

    fun clickOnEditButton(): ProfileScreenPageObj {
        rule.onNodeWithTag(editButton).assertIsDisplayed()
        rule.onNodeWithTag(editButton).performClick()
        return this
    }

    fun clickOnLogoutButton(): ProfileScreenPageObj {
        rule.onNodeWithTag(logoutButton).assertIsDisplayed()
        rule.onNodeWithTag(logoutButton).performClick()
        return this
    }

}