package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


@OptIn(ExperimentalTestApi::class)
class EditScreenPageObj(private val rule: ComposeContentTestRule) {

    private val screenTitle = Tags.EditProfileScreen.screenTitle
    private val nameTextField = Tags.EditProfileScreen.nameTextField
    private val saveButton = Tags.EditProfileScreen.saveButton
    private val cancelButton = Tags.EditProfileScreen.cancelButton


    fun waitForScreen(): EditScreenPageObj {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(screenTitle)
        )
        return this
    }

    fun assertScreenIsVisible(): EditScreenPageObj {
        rule.onNodeWithTag(screenTitle).assertIsDisplayed()
        return this
    }

    fun clearNameField(): EditScreenPageObj {
        rule.onNodeWithTag(nameTextField).assertIsDisplayed()
        rule.onNodeWithTag(nameTextField).performTextClearance()
        return this
    }

    fun inputName(name: String): EditScreenPageObj {
        rule.onNodeWithTag(nameTextField).assertIsDisplayed()
        rule.onNodeWithTag(nameTextField).performTextInput(name)
        return this
    }

    fun clickOnSaveButton(): EditScreenPageObj {
        Thread.sleep(1000L)
        rule.onNodeWithTag(saveButton).assertIsDisplayed()
        rule.onNodeWithTag(saveButton).performClick()
        return this
    }

    fun clickOnCancelButton(): EditScreenPageObj {
        rule.onNodeWithTag(cancelButton).assertIsDisplayed()
        rule.onNodeWithTag(cancelButton).performClick()
        return this
    }

}