package Pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
class ShokLoginPage(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    private val emailInput get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.emailInput)
    private val passwordInput get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordInput)
    private val loginButton get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
    private val backButton get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.backButton)
    private val registButton get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.registButton)
    private val pageTitle get() = composeTestRule.onNodeWithTag(Tags.LoginScreen.screenTitle)


    fun login(email: String, password: String) {
        emailInput.performTextInput(email)
        passwordInput.performTextInput(password)
        loginButton.performClick()
    }

    fun goToRegistration() {
        registButton.performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    fun goBack() {
        backButton.performClick()
    }


}