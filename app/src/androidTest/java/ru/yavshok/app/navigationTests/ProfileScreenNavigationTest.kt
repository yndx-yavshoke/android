package ru.yavshok.app.navigationTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.UserData
import ru.yavshok.app.pages.EditProfileScreenPage
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.pages.ProfileScreenPage


@RunWith(AndroidJUnit4::class)
class ProfileScreenNavigationTest {

    @get: Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var mainScreen: MainScreenPage
    private lateinit var editProfileScreen: EditProfileScreenPage

    @Before
    fun setup() {
        loginScreen = LoginScreenPage(composeRuleActivity)
        mainScreen = MainScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)
        editProfileScreen = EditProfileScreenPage(composeRuleActivity)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateFromProfileToEditProfileScreen() {
        mainScreen.waitForTitle()
        mainScreen.title.assertIsDisplayed()
        mainScreen.goToLoginButton.performClick()

        loginScreen.waitForTitle()
        loginScreen.title.assertIsDisplayed()
        mainScreen.title.assertDoesNotExist()


        loginScreen.fullLogin(UserData.TEST_EMAIL, UserData.TEST_PASSWORD)
        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag("profile_screen.profile_logout_button")
        )
        profileScreen.logoutButton.assertIsDisplayed()
        loginScreen.title.assertDoesNotExist()

        profileScreen.clickEditButton()
        editProfileScreen.title.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateFromProfileToMainScreen() {
        mainScreen.waitForTitle()
        mainScreen.title.assertIsDisplayed()
        mainScreen.goToLoginButton.performClick()

        loginScreen.waitForTitle()
        loginScreen.title.assertIsDisplayed()
        mainScreen.title.assertDoesNotExist()


        loginScreen.fullLogin(UserData.TEST_EMAIL, UserData.TEST_PASSWORD)
        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag("profile_screen.profile_logout_button")
        )
        profileScreen.logoutButton.assertIsDisplayed()
        loginScreen.title.assertDoesNotExist()

        profileScreen.clickLogoutButton()
        mainScreen.title.assertIsDisplayed()
    }
}