package ru.yavshok.app.fixtures.screens

import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class EditProfileScreenPage (private val composeRule: ComposeTestRule) {
    val title = composeRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    val nameTitleText = composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTitleText)
    val nameTextField = composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
    val errorMessage = composeRule.onNodeWithTag(Tags.EditProfileScreen.errorMessage)
    val saveButton = composeRule.onNodeWithTag(Tags.EditProfileScreen.saveButton)
    val cancelButton = composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton)

    fun checkTitleIsDisplayed(): EditProfileScreenPage {
        title.assertIsDisplayed()
        return this
    }

    fun checkTitleTextIsDisplayed(expected: String = "Edit Profile"): EditProfileScreenPage {
        title.assertTextContains(expected)
        return this
    }

    fun checkNameTitleTextIsDisplayed(expected: String = "Name"): EditProfileScreenPage {
        nameTitleText.assertTextContains(expected)
        return this
    }

    fun checkNameFieldIsDisplayed(): EditProfileScreenPage {
        nameTextField.assertIsDisplayed()
        return this
    }

    fun checkSaveButtonIsDisplayed(): EditProfileScreenPage {
        saveButton.assertIsDisplayed()
        saveButton.assertTextContains("Save Changes")
        return this
    }

    fun checkCancelButtonIsDisplayed(): EditProfileScreenPage {
        cancelButton.assertIsDisplayed()
        cancelButton.assertTextContains("Cancel")
        return this
    }

    fun checkErrorText(expected: String): EditProfileScreenPage {
        errorMessage.assertIsDisplayed()
        errorMessage.assertTextContains(expected)
        return this
    }

    fun typeName(newName: String): EditProfileScreenPage {
        nameTextField.performTextClearance()
        nameTextField.performTextInput(newName)
        return this
    }

    fun clickSave(): EditProfileScreenPage {
        saveButton.performClick()
        return this
    }

    fun clickCancel(): EditProfileScreenPage {
        cancelButton.performClick()
        return this
    }


    @OptIn(ExperimentalTestApi::class)
    fun waitExistTitle(): EditProfileScreenPage {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.EditProfileScreen.screenTitle),
            timeoutMillis = 5_000L
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitSaveButtonWithText(expectedText: String = "Save Changes"): EditProfileScreenPage {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            try {
                composeRule.onNodeWithTag(Tags.EditProfileScreen.saveButton)
                    .assertTextContains(expectedText)
                true
            } catch (e: AssertionError) {
                false
            }
        }
        return this
    }
}