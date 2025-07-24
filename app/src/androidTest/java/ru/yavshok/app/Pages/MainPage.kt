import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performTextReplacement
import ru.yavshok.app.Tags

class MainPage(private val rule: ComposeTestRule) {

    //this is for checking the main screen is is exist
    fun checkScreenVisible() {
        rule.onNode(hasTestTag(Tags.MainScreen.screenContainer)).assertIsDisplayed()
    }
    //this fuc for typing email in the input
    fun typeEmail(email: String) {
        rule.onNode(hasTestTag(Tags.MainScreen.emailTextField)).performTextReplacement(email)
    }

    fun checkUser(){
        rule.onNode(hasTestTag(Tags.MainScreen.checkButton)).performClick()
    }

    fun waitForUserIn() {
        rule.waitUntil(timeoutMillis = 15000) {
            rule.onAllNodes(hasTestTag(Tags.MainScreen.happyCatImg))
                .fetchSemanticsNodes().isNotEmpty()
        }
    }

    fun waitForUserOut() {
        rule.waitUntil(timeoutMillis = 15000) {
            rule.onAllNodes(hasTestTag(Tags.MainScreen.alreadyOut))
                .fetchSemanticsNodes().isNotEmpty()
        }
    }

    fun checkResultIn() {
        rule.onNode(hasTestTag(Tags.MainScreen.happyCatImg)).assertIsDisplayed()
        rule.onNode(hasTestTag(Tags.MainScreen.alreadyIn)).assertIsDisplayed()
    }
    fun checkResultOut() {
        rule.onNode(hasTestTag(Tags.MainScreen.alreadyOut)).assertIsDisplayed()
    }
    //this for click login button
    fun clickLogin() {
        rule.onNode(hasTestTag(Tags.MainScreen.loginButton)).performClick()
    }

    fun waitForScreenVisible(timeout: Long = 5000) {
        rule.waitUntil(timeoutMillis = timeout) {
            runCatching {
                checkScreenVisible()
                true
            }.getOrDefault(false)
        }
    }

}
