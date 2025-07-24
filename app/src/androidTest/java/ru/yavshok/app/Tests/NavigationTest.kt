package ru.yavshok.app.Tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.Fixtures.EditPage
import ru.yavshok.app.Fixtures.LoginPage
import ru.yavshok.app.Fixtures.MainPage
import ru.yavshok.app.Fixtures.ProfilePage
import ru.yavshok.app.Fixtures.RegisterPage
import ru.yavshok.app.utils_data.login
import ru.yavshok.app.utils_data.logout
import ru.yavshok.app.utils_data.waitForTag


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)


class NavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainPage = MainPage(composeRule)
    val loginPage = LoginPage(composeRule)
    val profilePage = ProfilePage(composeRule)
    val registerPage = RegisterPage(composeRule)
    val editProfilePage = EditPage(composeRule)


    @Test
    fun  fromMainScreenToLoginScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainPage.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginPage.screenTitle)

        loginPage.assertTitleIsDisplayed()
        mainPage.assertTitleDoesNotExist()
    }

    @Test
    fun fromLoginScreenToMainScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainPage.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginPage.screenTitle)

        loginPage.assertBackButtonIsDisplayed()
            .clickBackButton()

        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainPage.assertTitleIsDisplayed()
        loginPage.assertTitleDoesNotExist()
    }

    @Test
    fun fromLoginScreenToRegisterScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainPage.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginPage.screenTitle)

        loginPage.assertRegisterButtonIsDisplayed()
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterPage.emailTxtField)

        registerPage.assertEmailFieldIsDisplayed()
        loginPage.assertTitleDoesNotExist()
    }

    @Test
    fun fromRegisterScreenToLoginScreen() {
        composeRule.waitForTag(Tags.MainScreen.screenTitle)

        mainPage.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

        composeRule.waitForTag(Tags.LoginPage.screenTitle)

        loginPage.assertRegisterButtonIsDisplayed()
            .clickRegisterButton()

        composeRule.waitForTag(Tags.RegisterPage.screenTitle)

        registerPage.assertBackButtonIsDisplayed()
            .clickBackButton()

        composeRule.waitForTag(Tags.LoginPage.screenTitle)

        loginPage.assertTitleIsDisplayed()
        registerPage.assertScreenTitleDoesNotExist()
    }

    @Test
    fun fromProfileScreenToEditProfileScreen() {
        login(composeRule)

        profilePage.assertEditProfileButtonIsDisplayed()
            .clickEditProfileButton()

        composeRule.waitForTag(Tags.EditPage.screenTitle)

        profilePage.assertLogoutButtonDoesNotExist()
        editProfilePage.assertScreenTitleIsDisplayed()
            .clickCancelButton()

        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfilePage.logoutButton),
            timeoutMillis = 5000
        )

        logout(composeRule)
    }

    @Test
    fun fromEditProfileScreenToProfileScreen() {
        login(composeRule)

        profilePage.assertEditProfileButtonIsDisplayed()
            .clickEditProfileButton()

        composeRule.waitForTag(Tags.EditPage.screenTitle)

        editProfilePage.assertCancelButtonIsDisplayed()
            .clickCancelButton()

        composeRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(Tags.ProfilePage.logoutButton),
            timeoutMillis = 5000
        )

        logout(composeRule)
    }

    @Test
    fun fromProfileScreenToMainScreen() {
        login(composeRule)
        logout(composeRule)

        mainPage.assertTitleIsDisplayed()
        profilePage.assertLogoutButtonDoesNotExist()
    }


}