import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performTextReplacement
import ru.yavshok.app.Tags

class LoginPage(private val rule: ComposeTestRule) {

    fun checkScreenVisible() {
        rule.onNode(hasTestTag(Tags.LoginScreen.screenContainer)).assertIsDisplayed()
    }

    fun typeEmailAndPass(email: String, pass: String) {
        rule.onNode(hasTestTag(Tags.LoginScreen.emailTextField)).performTextReplacement(email)
        rule.onNode(hasTestTag(Tags.LoginScreen.passTextField)).performTextReplacement(pass)
    }

    fun clickLogin(){
        rule.onNode(hasTestTag(Tags.LoginScreen.loginButton)).performClick()
    }

    fun waitForErrorMessage(timeout: Long = 5000){
        rule.waitUntil(timeoutMillis = timeout) {
            runCatching {
                rule.onNode(hasTestTag(Tags.LoginScreen.errorMessage)).assertIsDisplayed()
                true
            }.getOrDefault(false)
        }
    }
}