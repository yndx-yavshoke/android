package ru.yavshok.app.Fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


class EditPage (private val rule: ComposeTestRule) {

    fun assertScreenTitleIsDisplayed(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertNameTextFieldIsDisplayed(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.nameTxtField).assertIsDisplayed()
        return this
    }

    fun assertSaveChangesButtonIsDisplayed(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.saveChangesButton).assertIsDisplayed()
        return this
    }

    fun assertCancelButtonIsDisplayed(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.cancelButton).assertIsDisplayed()
        return this
    }

    fun enterName(name: String): EditPage {
        rule.onNodeWithTag(Tags.EditPage.nameTxtField).performTextInput(name)
        return this
    }

    fun clickSaveButton(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.saveChangesButton). performClick()
        return this
    }

    fun clickCancelButton(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.cancelButton). performClick()
        return this
    }

    fun assertNameContainsText(name:String): EditPage {
        rule.onNodeWithTag(Tags.EditPage.nameTxtField).assertTextContains(name)
        return this
    }

    fun clearNameField(): EditPage {
        rule.onNodeWithTag(Tags.EditPage.nameTxtField).performTextClearance()
        return this
    }
}