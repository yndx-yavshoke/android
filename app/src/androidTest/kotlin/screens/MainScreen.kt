package screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import faker.FakeDataFactory
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class MainScreen(private val rule:ComposeTestRule) {

    private val faker = FakeDataFactory

    val title = rule.onNodeWithTag(Tags.MainScreen.screenTitle)
    val inputEmail = rule.onNodeWithTag(Tags.MainScreen.emailTextField)
    val yaVShokeButton = rule.onNodeWithTag(Tags.MainScreen.yavshokeButton)
    val toLoginScreenButton = rule.onNodeWithTag(Tags.MainScreen.loginButton)
    val successExistImage = rule.onNodeWithContentDescription("Happy Cat")
    val successExistLabel = rule.onNodeWithText("Ты уже в ШОКе")
    val nonExistLabel = rule.onNodeWithText("Ты еще не в ШОКе")

    fun checkDefaultState() {
        title.assertIsDisplayed()
        inputEmail.assertIsDisplayed()
        inputEmail.assertIsDisplayed()
        yaVShokeButton.assertIsNotEnabled()
        toLoginScreenButton.assertHasClickAction()
    }

    fun enterInvalidEmail() {
        inputEmail.performTextInput(faker.createInvalidEmail())
        yaVShokeButton.performClick()
    }

    fun enterValidEmail() {
        inputEmail.performTextInput(faker.createValidEmail())
        yaVShokeButton.assertHasClickAction()
        yaVShokeButton.performClick()
    }

    fun checkIfImageAndLabelIsDisplayed() {
        successExistImage.assertExists()
        successExistLabel.assertIsDisplayed()
    }


    fun checkIfImageAndLabelIsNotDisplayed() {
        nonExistLabel.assertIsDisplayed()
    }

    fun navigateToLoginScreen() {
        toLoginScreenButton.performClick()
    }
}

