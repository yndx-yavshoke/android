package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.Ignore
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.fixtures.EditProfileScreen
import ru.yavshok.app.fixtures.LoginScreen
import ru.yavshok.app.fixtures.MainScreen
import ru.yavshok.app.fixtures.ProfileScreen
import ru.yavshok.app.fixtures.RegisterScreen
import ru.yavshok.app.test_utils.loginThroughUI
import ru.yavshok.app.test_utils.logoutThroughUI
import ru.yavshok.app.test_utils.waitForTag


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)


class NavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainScreen = MainScreen(composeRule)
    val loginScreen = LoginScreen(composeRule)
    val profileScreen = ProfileScreen(composeRule)
    val registerScreen = RegisterScreen(composeRule)
    val editProfileScreen = EditProfileScreen(composeRule)


    @Test
    fun  shouldNavigateFromMainScreenToLoginScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertTitleIsDisplayed()
        mainScreen.assertTitleDoesNotExist()
    }

    @Test
    fun shouldNavigateFromLoginScreenToMainScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertBackButtonIsDisplayed()
            .clickBackButton()

        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
        loginScreen.assertTitleDoesNotExist()
    }

    @Test
    fun shouldNavigateFromLoginScreenToRegisterScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertRegisterButtonIsDisplayed()
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterScreen.screenTitle)

        registerScreen.assertScreenTitleIsDisplayed()
        loginScreen.assertTitleDoesNotExist()
    }

    @Test
    fun shouldNavigateFromRegisterScreenToLoginScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertRegisterButtonIsDisplayed()
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterScreen.screenTitle)

        registerScreen.assertBackButtonIsDisplayed()
            .clickBackButton()

        composeRule.waitForTag(Tags.LoginScreen.screenTitle)

        loginScreen.assertTitleIsDisplayed()
        registerScreen.assertScreenTitleDoesNotExist()
    }

    @Test
    fun shouldNavigateFromProfileScreenToEditProfileScreen() {
        loginThroughUI(composeRule)
        
        profileScreen.assertEditProfileButtonIsDisplayed()
            .clickEditProfileButton()

        composeRule.waitForTag(Tags.EditProfileScreen.screenTitle)

        profileScreen.assertLogoutButtonDoesNotExist()
        editProfileScreen.assertScreenTitleIsDisplayed()
            .clickCancelButton()

        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfileScreen.logoutButton),
            timeoutMillis = 7000
        )
        
        logoutThroughUI(composeRule)
    }

    @Test
    fun shouldNavigateFromEditProfileScreenToProfileScreen(){
        loginThroughUI(composeRule)
        
        profileScreen.assertEditProfileButtonIsDisplayed()
            .clickEditProfileButton()

        composeRule.waitForTag(Tags.EditProfileScreen.screenTitle)

        editProfileScreen.assertCancelButtonIsDisplayed()
            .clickCancelButton()

        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfileScreen.logoutButton),
            timeoutMillis = 7000
        )
        
        logoutThroughUI(composeRule)
    }

    @Test
    fun shouldNavigateFromProfileScreenToMainScreen() {
        loginThroughUI(composeRule)
        logoutThroughUI(composeRule)

        mainScreen.assertTitleIsDisplayed()
        profileScreen.assertLogoutButtonDoesNotExist()
    }

}