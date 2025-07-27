package Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags

class ShokEditPage(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    private val title get() = composeTestRule.onNodeWithText("Edit Profile")
    private val saveButton get() = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.saveButton)
    private val cancelButton get() = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton)
    private val nameInput get() = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.nameInput)

    fun verifyScreenDisplayed() {
        title.assertExists()
        nameInput.assertExists()
    }

    fun saveChanges(name: String) {
        nameInput.performTextInput(name)
        saveButton.performClick()

    }

    fun cancelChanges() {
        cancelButton.performClick()
    }

    fun verifyNameFieldContent(expectedText: String) {
        nameInput.assertTextEquals(expectedText)
    }
}