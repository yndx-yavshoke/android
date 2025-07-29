package ru.yavshok.app.pages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.ExperimentalTextApi
import ru.yavshok.app.Tags

@OptIn(ExperimentalTextApi::class)
class EditProfileScreenPage(private val composeRule: ComposeTestRule) {
    private val screenTitle = Tags.EditProfileScreen.screenTitle
    private val nameTextField = Tags.EditProfileScreen.nameTextField
    private val saveChangesButton = Tags.EditProfileScreen.saveChangesButton
    private val cancelButton = Tags.EditProfileScreen.cancelButton

    fun displayScreenTitle() = apply {
        composeRule.onNodeWithTag(screenTitle).assertIsDisplayed()
    }

    fun displayNameTextField() = apply {
        composeRule.onNodeWithTag(nameTextField).assertIsDisplayed()
    }

    fun displaySaveChangesButton() = apply {
        composeRule.onNodeWithTag(saveChangesButton).assertIsDisplayed()
    }

    fun displayCancelButton() = apply {
        composeRule.onNodeWithTag(cancelButton).assertIsDisplayed()
    }

    fun clickSaveChangesButton() = apply {
        composeRule.onNodeWithTag(saveChangesButton).performClick()
    }

    fun clickCancelButton() = apply {
        composeRule.onNodeWithTag(cancelButton).performClick()
    }


}