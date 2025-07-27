import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
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

@RunWith(AndroidJUnit4::class)
class MainScreenNavigationTests {

    private lateinit var screen : MainScreen

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setup() {
        composeRule.waitForIdle()
        screen = MainScreen(composeRule)
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
    }


    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        screen.checkDefaultState()

        screen.navigateToLoginScreen()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.LoginScreen.screenTitle).fetchSemanticsNodes().isNotEmpty()
        }
        val loginScreen = LoginScreen(composeRule)
        loginScreen.checkDefaultState()
    }
}