package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class EditorScreenPageObject(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    private val nameTitle = testRule.onNodeWithTag(Tags.EditProfileScreen.nameTitle)
    private val nameField = testRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
    private val saveButton = testRule.onNodeWithTag(Tags.EditProfileScreen.saveButton)
    private val cancelButton = testRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton)

     companion object {
        private const val EDIT_PROFILE_TITLE = "Edit Profile"
        private const val NAME_FIELD_TITLE = "Name"
        private const val NAME_FIELD_HINT = "Enter your name"
        private const val SAVE_BUTTON_TEXT = "Save Changes"
        private const val CANCEL_BUTTON_TEXT = "Cancel"
        private const val WAIT_TIMEOUT = 5000L
    }

    fun enterName(name: String): EditorScreenPageObject {
        nameField
            .assertIsDisplayed()
            .performTextClearance()
            .performTextInput(name)
        return this
    }

    fun clickSave(): EditorScreenPageObject {
        saveButton
            .assertIsDisplayed()
            .performClick()
        return this
    }

    fun clickCancel(): EditorScreenPageObject {
        cancelButton
            .assertIsDisplayed()
            .performClick()
        return this
    }


    @OptIn(ExperimentalTestApi::class)
    fun waitTitle(): EditorScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.EditProfileScreen.screenTitle),
            WAIT_TIMEOUT
        )
        return this
    }

    fun changeName(name: String): EditorScreenPageObject {
        return enterName(name).clickSave()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitErrorMessage(message: String): EditorScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message),
            WAIT_TIMEOUT
        )
        return this
    }
    
    fun assertErrorMessage(message: String): EditorScreenPageObject {
        testRule.onNodeWithText(message)
            .assertIsDisplayed()
        return this
    }

    fun assertTitle(): EditorScreenPageObject {
        title
            .assertIsDisplayed()
            .assertTextContains("Edit Profile")
        return this
    }

    fun assertFieldTitle(): EditorScreenPageObject {
        nameTitle
            .assertIsDisplayed()
            .assertTextContains("Name")
        return this
    }

    fun assertNameField(): EditorScreenPageObject {
        nameField
            .assertIsDisplayed()
            .assertTextContains("Enter your name")
        return this
    }

    fun assertSaveButtonIsNotEnabled(): EditorScreenPageObject {
        saveButton
            .assertIsDisplayed()
            .assertIsNotEnabled()
        return this
    }

    fun assertSaveButtonIsEnabled(): EditorScreenPageObject {
        saveButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Save Changes")
        return this
    }

    fun assertCancelButton(): EditorScreenPageObject {
        cancelButton
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertTextContains("Cancel")
        return this
    }
}