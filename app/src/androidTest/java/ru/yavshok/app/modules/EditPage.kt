package ru.yavshok.app.modules

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class EditPage(private val rule: ComposeTestRule) {
    private val title = Tags.EditScreen.screenTitle
    private val nameTextField = Tags.EditScreen.nameTextField
    private val saveButton = Tags.EditScreen.saveButton
    private val cancelButton = Tags.EditScreen.cancelButton
    private val fieldError = Tags.EditScreen.fieldError

    fun isVisibleTitle() {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun isVisibleNameTextField() {
        rule.onNodeWithTag(nameTextField).assertIsDisplayed()
    }

    fun isVisibleSaveButton() {
        rule.onNodeWithTag(saveButton).assertIsDisplayed()
    }

    fun isVisibleCancelButton() {
        rule.onNodeWithTag(cancelButton).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun isVisibleFieldError() {
        rule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher =  hasText("Name must be 50 characters or less"))
        rule.onNodeWithTag(fieldError).assertIsDisplayed()
    }

    fun clickSave(){
        rule.onNodeWithTag(saveButton).performClick()
    }

    fun clickCancel(){
        rule.onNodeWithTag(cancelButton).performClick()
    }
    fun clearName(){
        rule.onNodeWithTag(Tags.EditScreen.nameTextField).performTextClearance()
    }
    fun fillName(name: String){
        rule.onNodeWithTag(nameTextField).performTextInput(name)
    }

    fun nameIs(value: String){
        rule.onNodeWithTag(nameTextField).assertTextEquals(value)
    }

    fun errorIs(value: String){
        rule.onNodeWithTag(fieldError).assertTextEquals(value)
    }

    fun isSaveNotEnabled(){
        rule.onNodeWithTag(saveButton).assertIsNotEnabled()
    }

}