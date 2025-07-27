package screens

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import faker.FakeDataFactory
import ru.yavshok.app.Tags

class RegisterScreen(private val rule : ComposeTestRule) {

    private var faker = FakeDataFactory

    val title = rule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val inputEmail = rule.onNodeWithTag(Tags.RegisterScreen.inputEmail)
    val inputPassword = rule.onNodeWithTag(Tags.RegisterScreen.inputPassword)
    val inputAge = rule.onNodeWithTag(Tags.RegisterScreen.inputAge)
    val registerButton = rule.onNodeWithTag(Tags.RegisterScreen.registerButton)
    val backButton = rule.onNodeWithTag(Tags.RegisterScreen.backButton)
    val errorMessage = rule.onNodeWithTag(Tags.RegisterScreen.errorMessage)


    fun checkDefaultState() {
        title.assertIsDisplayed()
        inputEmail.assertIsDisplayed()
        inputPassword.assertIsDisplayed()
        inputAge.assertIsDisplayed()
        registerButton.assertIsEnabled()
        backButton.assertHasClickAction()
        registerButton.assertHasClickAction()
    }

    fun registerWithCorrectFields() {
        val newUser = faker.createNewUser()
        inputEmail.performTextInput(newUser.email)
        inputPassword.performTextInput((newUser.password))
        inputAge.performTextInput(newUser.age)

        registerButton.performClick()
    }

    fun registerWithEmptyFields() {
        val newUser = faker.createWithEmptyFields()
        inputEmail.performTextInput(newUser.email)
        inputPassword.performTextInput(newUser.password)
        inputAge.performTextInput(newUser.age)

        registerButton.performClick()
        errorMessage.assertIsDisplayed()
    }

}