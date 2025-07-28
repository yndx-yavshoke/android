package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.printToLog
import ru.yavshok.app.Tags
import ru.yavshok.app.test_utils.loginThroughUI


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)

class ProfileScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val profileScreen = ProfileScreen(composeRule)



    @Test
    fun shouldLoginAndGoToProfileScreen() {
        loginThroughUI(composeRule)
        composeRule.waitUntil(timeoutMillis = 10_000) {
            val found = composeRule.onAllNodesWithTag(Tags.ProfileScreen.userName).fetchSemanticsNodes().isNotEmpty()
            found
        }
        composeRule.onAllNodesWithTag(Tags.ProfileScreen.userName).printToLog("PROFILE")
        profileScreen.assertUserNameIsDisplayed()
    }

}