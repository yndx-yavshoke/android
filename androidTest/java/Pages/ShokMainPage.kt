package Pages

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags

class ShokMainPage(
    private val composeTestRule: ComposeContentTestRule
) {
    // Локаторы элементов
    private val title get() = composeTestRule.onNodeWithTag(Tags.MainScreen.screenTitle)
    private val emailInput get() = composeTestRule.onNodeWithTag(Tags.MainScreen.emailInput)
    private val checkButton get() = composeTestRule.onNodeWithTag(Tags.MainScreen.ShokButton)
    private val loginButton get() = composeTestRule.onNodeWithTag(Tags.MainScreen.loginButton)

    // Методы взаимодействия
    fun verifyScreenDisplayed() {
        title.assertExists()
        emailInput.assertExists()
    }

    fun checkEmail(email: String) {
        emailInput.performTextInput(email)
        checkButton.performClick()
    }

    fun navigateToLogin() {
        loginButton.performClick()
    }

}