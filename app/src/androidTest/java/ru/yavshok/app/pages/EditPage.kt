package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.data.Data
import ru.yavshok.app.data.Timeouts

class EditPage(private val composeRule: ComposeTestRule) {
    val nameField = composeRule.onNodeWithTag(Tags.EditScreen.nameTextField)
    val errorMessage = composeRule.onNodeWithTag(Tags.EditScreen.errorMessage)
    val saveButton = composeRule.onNodeWithTag(Tags.EditScreen.saveButton)
    val cancelButton = composeRule.onNodeWithTag(Tags.EditScreen.cancelButton)

    fun shouldTypeAndSaveNewName() {
        with(nameField) {
            assertIsDisplayed()
            performClick()
            performTextClearance()
            performTextInput(Data.NEW_NAME)
        }
        with(saveButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    fun shouldTypeEmptyName() {
        with(nameField) {
            assertIsDisplayed()
            performClick()
            performTextClearance()
            performTextInput("")
        }
    }

    fun shouldTypeLongName() {
        with(nameField) {
            assertIsDisplayed()
            performClick()
            performTextClearance()
            performTextInput(Data.LONG_NAME)
        }
    }

    fun shouldCancelEditing() {
        with(nameField) {
            assertIsDisplayed()
            performClick()
        }
        with(cancelButton) {
            assertIsDisplayed()
            performClick()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitExists() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = Timeouts.SHORT,
            matcher = hasTestTag(Tags.EditScreen.screenTitle)
        )
    }
}
