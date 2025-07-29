package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class EditProfileScreen(private val composeRule: ComposeTestRule) {

    private var titleLabel = Tags.EditProfileScreen.titleLabel
    private var nameField = Tags.EditProfileScreen.nameField

    private var saveButton = Tags.EditProfileScreen.saveButton
    private var cancelButton = Tags.EditProfileScreen.cancelButton

    fun waitToLoad() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(titleLabel),
            timeoutMillis = 5_000L
        )
    }

    fun checkNameFieldIsDisplayed() {
        composeRule.onNodeWithTag(nameField).assertIsDisplayed()
    }

    fun checkSaveButtonIsDisplayed() {
        composeRule.onNodeWithTag(saveButton).assertIsDisplayed()
    }

    fun checkCancelButtonIsDisplayed() {
        composeRule.onNodeWithTag(cancelButton).assertIsDisplayed()
    }
}