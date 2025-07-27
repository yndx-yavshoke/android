import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.viewmodel.ViewModelFactory
import screens.LoginScreen
import screens.MainScreen
import screens.RegisterScreen


@RunWith(AndroidJUnit4::class)
class LoginScreenNavigationTests {
    private lateinit var mainScreen : MainScreen
    private lateinit var loginScreen : LoginScreen
    private lateinit var registerScreen : RegisterScreen

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
    fun shouldNavigateFromLoginScreenToMainScreen() {
        loginScreen.navigateBackToMainScreen()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
        mainScreen = MainScreen(composeRule)
        mainScreen.checkDefaultState()
    }

    @Test
    fun shouldNavigateFromLoginScreenToRegisterScreen() {
        loginScreen.navigateToRegisterPage()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.RegisterScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
        registerScreen = RegisterScreen(composeRule)
        registerScreen.checkDefaultState()
    }
}