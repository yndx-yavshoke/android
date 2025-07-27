import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.MainScreen
import screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    private lateinit var screen: MainScreen

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        composeRule.waitForIdle()
        screen = MainScreen(composeRule)
    }

    @Test
    fun shouldDisplayElementsInDefaultState() {
        screen.checkDefaultState()
    }

    @Test
    fun shouldDisplayImageAndGreenLabelIfUserExists() {
        screen.enterValidEmail()
        composeRule.waitForIdle()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.userExistImage).fetchSemanticsNodes().isNotEmpty()
        }
        screen.checkIfImageAndLabelIsDisplayed()
    }

    @Test
    fun shouldDisplayLabelIfUserDoesNotExists() {
        screen.enterInvalidEmail()
        composeRule.waitForIdle()
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag(Tags.MainScreen.nonExistUser).fetchSemanticsNodes().isNotEmpty()
        }
        screen.checkIfImageAndLabelIsNotDisplayed()
    }
}
