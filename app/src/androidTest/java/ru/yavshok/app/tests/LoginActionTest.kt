package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.fixtures.LoginScreen
import ru.yavshok.app.fixtures.MainScreen
import ru.yavshok.app.fixtures.ProfileScreen
import ru.yavshok.app.test_utils.Users
import ru.yavshok.app.test_utils.logoutThroughUI
import ru.yavshok.app.test_utils.waitForTag


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)

class LoginActionTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainScreen = MainScreen(composeRule)
    val loginScreen = LoginScreen(composeRule)
    val profileScreen = ProfileScreen(composeRule)



    @Test
    fun shouldLoginRegisteredUser() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterEmail(Users.registeredUser.email)
            .enterPassword(Users.registeredUser.password)
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.ProfileScreen.userName)

        profileScreen.assertLogoutButtonIsDisplayed()
        loginScreen.assertTitleDoesNotExist()

        logoutThroughUI(composeRule)
    }

    @Test
    fun shouldNotLoginUnregisteredUser() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .enterEmail(Users.unregisteredUser.email)
            .enterPassword(Users.unregisteredUser.password)
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()
         composeRule.waitForTag(Tags.LoginScreen.errorMessage)
        loginScreen.assertErrorMessageIsDisplayed()

    }

}