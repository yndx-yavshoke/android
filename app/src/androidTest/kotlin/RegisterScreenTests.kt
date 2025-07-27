import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.ViewModelFactory
import screens.LoginScreen
import screens.MainScreen
import screens.RegisterScreen

@RunWith(AndroidJUnit4::class)
class RegisterScreenTests {

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
            composeRule.onAllNodesWithTag("main_screen.screen_title").fetchSemanticsNodes().isNotEmpty()
        }

        mainScreen.navigateToLoginScreen()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("login_screen.screen_title").fetchSemanticsNodes().isNotEmpty()
        }

        loginScreen = LoginScreen(composeRule)
        loginScreen.checkDefaultState()
        loginScreen.navigateToRegisterPage()
        registerScreen = RegisterScreen(composeRule)
        registerScreen.checkDefaultState()
    }
    @Test
    fun shouldDisplayErrorMessageIfFieldsAreEmpty () {
        registerScreen.registerWithEmptyFields()
    }

    @Test
    fun shouldRegisterSuccesfullyWithCorrectFields() {
        registerScreen.registerWithCorrectFields()

    }
}