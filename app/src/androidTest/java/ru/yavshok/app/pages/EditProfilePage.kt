package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.ValidUser


class EditProfilePage(composeRule: ComposeTestRule) : BasePage(composeRule) {
    private val title = Tags.EditProfileScreen.screenTitle
    private val nameTextField = Tags.EditProfileScreen.nameTextField
    private val saveButton = Tags.EditProfileScreen.saveButton
    private val cancelButton = Tags.EditProfileScreen.cancelButton

    override val nodeIsDisplayed = mutableListOf(
        title,
        nameTextField,
        saveButton,
        cancelButton,
    )

    fun fillName(name: String) {
        with(composeRule.onNodeWithTag(nameTextField)){
            performTextClearance()
            performTextInput(name)
        }
    }

    fun clickSaveButton() {
        composeRule.onNodeWithTag(saveButton).performClick()
    }

    fun clickCancelButton() {
        composeRule.onNodeWithTag(cancelButton).performClick()
    }

    fun setDefaultName() {
        fillName(ValidUser.DEFAULT_NAME)
        clickSaveButton()
    }
}

