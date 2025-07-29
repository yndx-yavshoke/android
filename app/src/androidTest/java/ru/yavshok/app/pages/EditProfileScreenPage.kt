package ru.yavshok.app.pages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput

class EditProfileScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag("edit_profile_screen.screen_title")
    private val nameEditInput = composeRule.onNodeWithTag("edit_profile_screen.name_edit_input")
    private val saveChangesButton = composeRule.onNodeWithTag("edit_profile_screen.save_changes_button")
    private val cancelButton = composeRule.onNodeWithTag("edit_profile_screen.cancel_button")

    fun fillEditNameInput(name: String): EditProfileScreenPage {
        nameEditInput.assertIsDisplayed()
        nameEditInput.performTextClearance()
        nameEditInput.performTextInput(name)
        return this
    }

    fun clickSaveChangesButton(): EditProfileScreenPage {
        saveChangesButton.assertIsDisplayed()
        saveChangesButton.performClick()
        return this
    }

    fun clickCancelButton(): EditProfileScreenPage {
        cancelButton.assertIsDisplayed()
        cancelButton.performClick()
        return this
    }
}
