package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class EditorScreenPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    val title = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    val nameTitle = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.nameTitle)
    val nameField = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
    val saveButton = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.saveButton)
    val cancelButton = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton)

    fun enterName(name: String): EditorScreenPageObject {
        nameField.assertIsDisplayed()
        nameField.performTextInput(name)
        return this
    }

    fun clickSave(): EditorScreenPageObject {
        saveButton.assertIsDisplayed()
        saveButton.performClick()
        return this
    }

    fun clickCancel(): EditorScreenPageObject {
        cancelButton.assertIsDisplayed()
        cancelButton.performClick()
        return this
    }

    fun changeName(email: String): EditorScreenPageObject {
        enterName(email).clickSave()
        composeTestRule.waitUntil(5000) {
            try {
                saveButton.assertTextContains("Save")
                true
            } catch (e: AssertionError) {
                false
            }
        }
        clickCancel()
        return this
    }
}