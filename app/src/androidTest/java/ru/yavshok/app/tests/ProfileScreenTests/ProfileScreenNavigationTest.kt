package ru.yavshok.app.tests.ProfileScreenTests

import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Test
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.NavigationRule
import ru.yavshok.helpers.assertExistsAndDisplayed
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists
import ru.yavshok.helpers.login

class ProfileScreenNavigationTest: NavigationRule() {

    private val profileImage = Tags.ProfileScreen.profileImage
    private val editProfileButton = Tags.ProfileScreen.editProfileButton
    private val logoutButton = Tags.ProfileScreen.logoutButton

    private val titleMain = Tags.MainScreen.screenTitle
    private val loginButtonOnMainScreen = Tags.MainScreen.loginButton

    private val titleLogin = Tags.LoginScreen.screenTitle

    private val titleEditProfile = Tags.EditProfileScreen.screenTitle

    @Before
    fun setup() {
        val email = BuildConfig.EMAIL
        val password = BuildConfig.PASSWORD

        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")

        composeRule.clickAndAssertExists(loginButtonOnMainScreen)
        composeRule.assertExistsAndDisplayed(titleLogin)
        composeRule.login(email, password)

        composeRule.assertExistsAndDisplayed(profileImage)
    }

    @Test
    fun goToMainScreenFromProfileScreen() {
        composeRule.clickAndAssertExists(logoutButton)

        composeRule.onNodeWithTag(logoutButton).assertDoesNotExist()
        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")
    }

    @Test
    fun goToEditProfileScreenFromProfileScreen() {
        composeRule.clickAndAssertExists(editProfileButton)

        composeRule.onNodeWithTag(editProfileButton).assertDoesNotExist()
        composeRule.assertTextDisplayed(titleEditProfile, "Edit Profile")
    }
}