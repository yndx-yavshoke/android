package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import ru.yavshok.app.Tags
import ru.yavshok.app.data.Timeouts
import ru.yavshok.app.fixture.fixture

class ProfilePage(private val composeRule: ComposeTestRule) {

    val header = composeRule.onNodeWithTag(Tags.ProfileScreen.header)
    val name = composeRule.onNodeWithTag(Tags.ProfileScreen.name, useUnmergedTree = true)
    val status = composeRule.onNodeWithTag(Tags.ProfileScreen.status)
    val editButton = composeRule.onNodeWithTag(Tags.ProfileScreen.editButton)
    val logoutIcon = composeRule.onNodeWithTag(Tags.ProfileScreen.logoutIcon)

    @OptIn(ExperimentalTestApi::class)
    fun waitExists() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = Timeouts.SHORT,
            matcher = hasTestTag(Tags.ProfileScreen.header)
        )
    }

    fun navigateToProfilePage() = fixture(composeRule) {
        with(mainPage) {
            waitExists()
            goToLogin()
        }

        with(loginPage) {
            waitExists()
            shouldAuthorizeUser()
        }

        with(profilePage) {
            waitExists()
        }
    }
}
