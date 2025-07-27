import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.viewmodel.ViewModelFactory
import screens.LoginScreen
import screens.MainScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    private lateinit var mainScreen : MainScreen
    private lateinit var loginScreen : LoginScreen

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setup() {
        composeRule.waitForIdle()
        mainScreen = MainScreen(composeRule)
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
        mainScreen.navigateToLoginScreen()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.LoginScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
        loginScreen = LoginScreen(composeRule)
        loginScreen.checkDefaultState()
    }

    @Test
    fun shouldDisplayElementsInDefaultState() {
        loginScreen.checkDefaultState()
    }

    @Test
    fun shouldDisplayErrorMessageIfFieldsAreEmpty() {
        loginScreen.showErrorMessageWhenFieldsAreEmpty()
    }

    @Test
    fun shouldDisplayErrorMessageIfEmailIsNotValid() {
        loginScreen.showErrorMessageWhenEmailIsNotValid()
    }

    @Test
    fun shouldDisplayErrorMessageIfPasswordIsWrong() {
        loginScreen.showErrorMessageWhenPasswordIsWrong()
    }

    @Test
    fun shouldLoginSuccesfullyWithValidEmailAndPassword() {
        loginScreen.loginSuccessfully()
        composeRule.waitForIdle()
    }

}