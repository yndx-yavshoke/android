package screens

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.github.javafaker.Faker
import faker.FakeDataFactory
import ru.yavshok.app.Tags

class EditProfileScreen(private val rule : ComposeTestRule) {

    private val faker = FakeDataFactory

    val screenTitle = rule.onNodeWithTag(Tags.EditProfileScreen.screenTitle)
    val nameLabel = rule.onNodeWithTag(Tags.EditProfileScreen.nameLabel)
    val inputName = rule.onNodeWithTag(Tags.EditProfileScreen.inputName)
    val saveChangesButton = rule.onNodeWithTag(Tags.EditProfileScreen.saveChangesButton)
    val cancelButton = rule.onNodeWithTag(Tags.EditProfileScreen.cancelButton)
    val errorMessage = rule.onNodeWithTag(Tags.EditProfileScreen.errorMessage)

    fun checkDefaultState() {
        screenTitle.assertIsDisplayed()
        nameLabel.assertIsDisplayed()
        inputName.assertIsDisplayed()
        inputName.assertTextEquals("")
        saveChangesButton.assertHasClickAction()
        cancelButton.assertHasClickAction()
    }

    fun editNameSuccesfully() {
        val newName = faker.createValidUsername()
        inputName.performTextInput(newName)
        saveChangesButton.performClick()
        cancelButton.performClick()
    }

    fun editNameWithEmptyName() {
        val emptyName = faker.createEmptyUsername()
        inputName.performTextInput(emptyName)
        saveChangesButton.performClick()
        cancelButton.performClick()
    }

}