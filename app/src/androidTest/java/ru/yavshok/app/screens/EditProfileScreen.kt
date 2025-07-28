package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class EditProfileScreen(private val rule: ComposeTestRule) {
    private val title get() = rule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    private val nameLabel get() = rule.onNodeWithTag(Tags.EditProfileScreen.nameLabel)
    private val nameField get() = rule.onNodeWithTag(Tags.EditProfileScreen.newNameTextField)
    private val errorText get() = rule.onNodeWithTag(Tags.EditProfileScreen.errorText)
    private val saveChangesButton get() = rule.onNodeWithTag(Tags.EditProfileScreen.saveChangesButton)
    private val backButton get() = rule.onNodeWithTag(Tags.EditProfileScreen.backButton)

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.EditProfileScreen.screenTitle)
        )
    }

    fun assertScreenDisplayed() {
        title.assertIsDisplayed()
        nameLabel.assertIsDisplayed()
        nameField.assertIsDisplayed()
        backButton.assertIsDisplayed()
        saveChangesButton.assertIsDisplayed()
    }

    fun typeName(name: String) {
        nameField.performTextClearance()
        nameField.performTextInput(name)
    }

    fun clickOnSave() {
        saveChangesButton.performClick()

    }

    fun clickOnBack() {
        backButton.performClick()
    }

    fun waitUntilButtonShowsSaveChanges() {
        rule.waitUntil(timeoutMillis = 3000) {
            try {
                saveChangesButton.assertTextEquals("Save Changes")
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }
}