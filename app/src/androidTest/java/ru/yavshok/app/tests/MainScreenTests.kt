package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.TestDataId
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mainScreen: MainScreenPage

    @Before
    fun setUp() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        mainScreen = MainScreenPage(composeRule)
    }

    @Test
    fun shouldHaveTitle() {
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun shouldDisplaySuccessState() {
        val screen = MainScreenPage(composeRule)

        screen.enterEmail("q@q.q")
            .clickCheckButton()

        composeRule.waitUntil(
            condition = {
                composeRule
                    .onAllNodesWithTag(Tags.MainScreen.successText)
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            },
            timeoutMillis = 10_000
        )

        screen.assertSuccessTextIsVisible()
        screen.assertGifIsVisible()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldDisplayErrorForNonexistentEmail() {
        val fakeEmail = TestDataId.nonRegisteredUser.email

        mainScreen.enterEmail(fakeEmail)
            .clickCheckButton()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.failText)
        )

        mainScreen.assertFailTextIsVisible("Ты еще не в ШОКе")
    }

}
