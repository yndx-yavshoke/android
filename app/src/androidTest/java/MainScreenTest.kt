import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.TestAuthCredentials

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    // Tags.MainScreen.screenTitle
    // Tags.MainScreen.emailTextField

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var testEmail: String

    @Before
    fun setUp() {

        testEmail = TestDataGenerator.generateRandomEmail()

        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = viewModel()
            )
        }

    }

    @Test
    fun shouldTypeEmailOnMainScreen () {
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testEmail)

    }

    @Test
    fun displayElementsOnMainScreen() {
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.screenContainer).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.shokSheckButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()

    }

    @Test
    fun checkDisplayMessageNotExistEmail () {
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(Tags.MainScreen.shokSheckButton).performClick()
        composeRule.waitUntil(1000) {
            try {
                composeRule.onNodeWithTag(Tags.MainScreen.notexistText).assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        composeRule.onNodeWithTag(Tags.MainScreen.notexistText).assertIsDisplayed()
    }

    @Test
    fun checkDisplayMessageExistEmail () {
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput(TestAuthCredentials.REGISTERED_USER_EMAIL)
        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains(TestAuthCredentials.REGISTERED_USER_EMAIL)
        composeRule.onNodeWithTag(Tags.MainScreen.shokSheckButton).performClick()
        composeRule.waitUntil(1000) {
            try {
                composeRule.onNodeWithTag(Tags.MainScreen.existText).assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
        composeRule.onNodeWithTag(Tags.MainScreen.existText).assertIsDisplayed()

    }





}