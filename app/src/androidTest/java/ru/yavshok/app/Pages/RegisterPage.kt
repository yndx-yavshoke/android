import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performTextReplacement
import ru.yavshok.app.Tags

class RegisterPage(private val rule: ComposeTestRule) {
    fun typeEmailAndPassAndAge(email: String, pass: String, age: String) {
        rule.onNode(hasTestTag(Tags.RegisterScreen.emailTextLable)).performTextReplacement(email)
        rule.onNode(hasTestTag(Tags.RegisterScreen.passLabel)).performTextReplacement(pass)
        rule.onNode(hasTestTag(Tags.RegisterScreen.ageLabel)).performTextReplacement(age)
    }
    fun clickRegister(){
        rule.onNode(hasTestTag(Tags.RegisterScreen.registerButton)).performClick()
    }
    fun handleRegisterScreen(){
        rule.onNode(hasTestTag(Tags.LoginScreen.registerButton)).performClick()
    }

    fun waitForErrorMessage(timeout: Long = 5000) {
        rule.waitUntil(timeoutMillis = timeout) {
            runCatching {
                rule.onNode(hasTestTag(Tags.RegisterScreen.errorMessage)).assertIsDisplayed()
                true
            }.getOrDefault(false)
        }
    }

}