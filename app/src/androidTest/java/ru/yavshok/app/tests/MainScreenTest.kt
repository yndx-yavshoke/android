package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.MainScreen
import ru.yavshok.app.test_utils.Users
import ru.yavshok.app.test_utils.waitForTag
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.Tags



@RunWith(AndroidJUnit4::class)


class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun launchMainScreen() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }


    val mainScreen = MainScreen(composeRule)


   @Test
    fun shouldTypeEmailOnMainScreen() {
       launchMainScreen()

       mainScreen.assertTitleIsDisplayed()
           .assertEmailFieldIsDisplayed()
           .enterEmail(Users.registeredUser.email)
           .assertEmailFieldContains(Users.registeredUser.email)
   }

    @Test
    fun shouldCheckRegisteredEmail() {
        launchMainScreen()

        mainScreen
            .assertEmailFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .assertEmailFieldContains(Users.registeredUser.email)
            .clickCheckButton()

        composeRule.waitForTag(Tags.MainScreen.successText)

        mainScreen.assertSuccessTextIsDisplayed()
            .assertHappyCatIsDisplayed()
    }

    @Test
    fun shouldCheckUnregisteredEmail() {
        launchMainScreen()

        mainScreen.assertEmailFieldIsDisplayed()
            .enterEmail(Users.unregisteredUser.email)
            .assertEmailFieldContains(Users.unregisteredUser.email)
            .clickCheckButton()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule
                .onAllNodesWithText("Ты еще не в ШОКе")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        mainScreen.assertUnsuccessTextIsDisplayed()
    }

    @Test
    fun checkButton_shouldEnableOnlyAfterEmailEntered() {
        launchMainScreen()

        mainScreen.assertEmailFieldIsDisplayed()
            .assertCheckButtonIsDisabled()
            .enterEmail(Users.unregisteredUser.email)
            .assertEmailFieldContains(Users.unregisteredUser.email)
            .assertCheckButtonIsEnabled()
    }


}
