package ru.yavshok.app.fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


class EditProfileScreen (private val rule: ComposeTestRule) {

    fun assertScreenTitleIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertNameLabelIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.nameLabel).assertIsDisplayed()
        return this
    }

    fun assertNameTextFieldIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).assertIsDisplayed()
        return this
    }

    fun assertSaveChangesButtonIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.saveChangesButton).assertIsDisplayed()
        return this
    }

    fun assertCancelButtonIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).assertIsDisplayed()
        return this
    }

    fun assertErrorMessageIsDisplayed(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.errorMessage).assertIsDisplayed()
        return this
    }

    fun enterName(name: String): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).performTextInput(name)
        return this
    }

    fun clickSaveButton(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.saveChangesButton). performClick()
        return this
    }

    fun clickCancelButton(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.cancelButton). performClick()
        return this
    }

    fun assertNameContainsText(name:String): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).assertTextContains(name)
        return this
    }

    fun clearNameField(): EditProfileScreen {
        rule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).performTextClearance()
        return this
    }
}