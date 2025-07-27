package Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ShokProfilePage(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    // Локаторы элементов
    private val editButton get() = composeTestRule.onNodeWithTag(Tags.ProfileScreen.editButton)
    private val logoutButton get() = composeTestRule.onNodeWithTag(Tags.ProfileScreen.logoutButton)

    // Методы взаимодействия
    fun verifyProfileDisplayed() {
        editButton.assertExists()
    }

    fun navigateToEditProfile() {
        editButton.performClick()
    }


    fun performLogout() {
        logoutButton.performClick()
    }


}