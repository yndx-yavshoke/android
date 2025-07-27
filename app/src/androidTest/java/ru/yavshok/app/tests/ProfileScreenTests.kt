package ru.yavshok.app.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.pages.ProfileScreenPage

@RunWith(AndroidJUnit4::class)
class ProfileScreenTests {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var profileScreen: ProfileScreenPage

    @Before
    fun setUp() {
        mainScreen = MainScreenPage(composeRule)
        profileScreen = ProfileScreenPage(composeRule)

        mainScreen
            .timeoutForTitle()
            .clickLoginPageButton()

        // for some reason the methods from LoginScreenPage couldn't be read :(
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput("q@q.q")
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .performTextInput("123456")
        composeRule.onNodeWithTag(Tags.LoginScreen.submitButton)
            .performClick()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            profileScreen.isUserNameDisplayed()
        }
    }

    @Test
    fun shouldDisplayAllProfileElements() {
        profileScreen
            .assertUserAvatarDisplayed()
            .assertUserNameDisplayed()
            .assertUserStatusDisplayed()
            .assertEditButtonDisplayed()
            .assertLogoutButtonDisplayed()
    }

    @Test
    fun shouldLogoutAndReturnToMainScreen() {
        profileScreen.clickLogoutButton()

        mainScreen
            .timeoutForTitle()
            .assertTitleIsDisplayed()
    }
}
