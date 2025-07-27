package Pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class ShokRegistPage(private val composeTestRule: ComposeTestRule) {

    // Локаторы элементов
    val screenTitle get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val emailInput get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.emailInput)
    val passwordInput get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.passwordInput)
    val ageInput get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.ageInput)
    val backButton get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.screenTitle)
    val registButton get() = composeTestRule.onNodeWithTag(Tags.RegisterScreen.registButton)

    // Методы взаимодействия
    fun register(email: String, password: String, age: Int) {
        emailInput.performTextInput(email)
        passwordInput.performTextInput(password)
        ageInput.performTextInput(age.toString())
        registButton.performClick()
    }

    fun goBack() {
        backButton.performClick()
    }

    fun assertPageDisplayed() {
        screenTitle.assertExists()
    }
}