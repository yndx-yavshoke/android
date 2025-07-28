package ru.yavshok.app.pages
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags


class EditProfilePage(composeTestRule: AndroidComposeTestRule<*, *>)
    : BasePage(composeTestRule){

    val nameField = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.nameField)
    val title = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    val saveChangesButton = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.SaveChangesButton)
    val cancelButton = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.CancelButton)
    val errorText = composeTestRule.onNodeWithTag(Tags.EditProfileScreen.ErrorText)


    fun clickSaveChangesButton(): EditProfilePage {
        saveChangesButton.performClick()
        return this
    }

    fun clickCancelButton(): EditProfilePage {
        cancelButton.performClick()
        return this
    }

    fun writeNewName(name: String): EditProfilePage {
        title.assertIsDisplayed()
        nameField.assertIsDisplayed()
        nameField.performTextClearance()
        nameField.performTextInput(name)


        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForTitle() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.EditProfileScreen.screenTitle)
        )
    }
}