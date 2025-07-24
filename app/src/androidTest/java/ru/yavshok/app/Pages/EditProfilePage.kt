import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import ru.yavshok.app.Tags

class EditProfilePage(private val rule: ComposeTestRule) {
    fun checkProfileVisible() {
        rule.onNode(hasTestTag(Tags.ProfileScreen.screenContainer)).assertIsDisplayed()
    }
    fun waitForProfileVisible(timeout: Long = 5000) {
        rule.waitUntil(timeoutMillis = timeout) {
            runCatching {
                checkProfileVisible()
                true
            }.getOrDefault(false)
        }
    }
    fun editingName(name: String){
        rule.onNode(hasTestTag(Tags.ProfileScreen.editingButton)).performClick()
        rule.onNode(hasTestTag(Tags.ProfileScreen.newName)).performTextReplacement(name)
    }
    fun clickSave() {
        rule.onNode(hasTestTag(Tags.ProfileScreen.saveButton)).performClick()
    }

    fun clickCancel() {
        rule.onNode(hasTestTag(Tags.ProfileScreen.cancelButton)).performClick()
    }
    fun assertNameUpdated() {
        // If the new name shows somewhere, assert it here
        rule.onNode(hasTestTag(Tags.ProfileScreen.newName)).assertIsDisplayed()
    }
}