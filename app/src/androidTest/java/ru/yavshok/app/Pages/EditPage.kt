package ru.yavshok.app.Pages

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

@OptIn(ExperimentalTestApi::class)
class EditScreenPage(private val rule: ComposeTestRule) {

    private val title = Tags.EditScreen.title
    private val lineName = Tags.EditScreen.nameLine
    private val nameTextField = Tags.EditScreen.nameTextField
    private val buttonSave = Tags.EditScreen.buttonSaveChanges
    private val cancel = Tags.EditScreen.buttonCancel
    private val placeholderName = "Name"

    fun displayedTitle() = apply {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun displayedLineName() = apply {
        rule.onNodeWithTag(lineName).assertIsDisplayed()
    }

    fun displayedNameTextField() = apply {
        rule.onNodeWithTag(nameTextField).assertIsDisplayed()
    }

    fun displayedButtonSave() = apply {
        rule.onNodeWithTag(buttonSave).assertIsDisplayed()
    }

    fun displayedCancel() = apply {
        rule.onNodeWithTag(cancel).assertIsDisplayed()
    }

    fun clearNameTextField() = apply {
        rule.onNodeWithTag(nameTextField).performTextClearance()
    }

    fun clickButtonSave() = apply {
        rule.onNodeWithTag(buttonSave).performClick()
    }

    fun inputName(name: String) = apply {
        rule.onNodeWithTag(nameTextField).performTextInput(name)
        rule.onNodeWithTag(nameTextField).assertTextContains(name)
    }

    fun placeholderNameTextField() = apply {
        rule.onNodeWithTag(nameTextField).assertTextContains(placeholderName)
    }

    fun clickButtonCancel() = apply {
        rule.onNodeWithTag(cancel).performClick()
    }

    fun timeoutTitle() = apply {
        rule.waitUntilExactlyOneExists(
            timeoutMillis = 5_00L,
            matcher = hasTestTag(title)
        )
    }

}
