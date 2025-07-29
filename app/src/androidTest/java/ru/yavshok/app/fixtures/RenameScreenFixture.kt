package ru.yavshok.app.fixtures

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import ru.yavshok.app.utility.ApplicationTextEnv

class EditProfileScreenFixture(private val testRule: ComposeTestRule) {
    private val title = testRule.onNodeWithText(ApplicationTextEnv.EditProfileScreen.title)
    private val nameTextField = testRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
    private val saveButton = testRule.onNodeWithText(ApplicationTextEnv.EditProfileScreen.saveButton)
    private val cancelButton = testRule.onNodeWithText(ApplicationTextEnv.EditProfileScreen.cancelButton)
    private val errorMessage = testRule.onNodeWithTag(Tags.EditProfileScreen.errorMessage)
    private val loadingIndicator = testRule.onNodeWithTag(Tags.EditProfileScreen.loadingIndicator)

    fun checkTitle(): EditProfileScreenFixture {
        title.assertIsDisplayed()
        return this
    }

    fun checkNameTextField(): EditProfileScreenFixture {
        nameTextField.assertIsDisplayed()
        return this
    }

    fun checkSaveButton(): EditProfileScreenFixture {
        saveButton.assertIsDisplayed()
        return this
    }

    fun checkCancelButton(): EditProfileScreenFixture {
        cancelButton.assertIsDisplayed()
        return this
    }

    fun checkSaveButtonEnabled(): EditProfileScreenFixture {
        saveButton.assertIsEnabled()
        return this
    }

    fun checkSaveButtonDisabled(): EditProfileScreenFixture {
        saveButton.assertIsNotEnabled()
        return this
    }

    fun enterName(name: String): EditProfileScreenFixture {
        nameTextField.assertIsDisplayed()
        nameTextField.performClick()
        nameTextField.performTextClearance()
        nameTextField.performTextInput(name)
        return this
    }

    fun clearNameField(): EditProfileScreenFixture {
        nameTextField.assertIsDisplayed()
        nameTextField.performClick()
        nameTextField.performTextClearance()
        return this
    }

    fun clickSaveButton(): EditProfileScreenFixture {
        saveButton.assertIsDisplayed()
        saveButton.performClick()
        return this
    }

    fun clickCancelButton(): EditProfileScreenFixture {
        cancelButton.assertIsDisplayed()
        cancelButton.performClick()
        return this
    }

    fun updateProfile(name: String): EditProfileScreenFixture {
        return enterName(name).clickSaveButton()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle(): EditProfileScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(ApplicationTextEnv.EditProfileScreen.title), 5000
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForErrorMessage(message: String): EditProfileScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTextExactly(message), 5000
        )
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForLoading(): EditProfileScreenFixture {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.EditProfileScreen.loadingIndicator), 5000
        )
        return this
    }
}