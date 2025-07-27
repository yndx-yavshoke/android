package ru.yavshok.app

import Pages.ShokLoginPage
import Pages.ShokProfilePage
import Users.TestUsers
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var profilePage: ShokProfilePage
    private lateinit var loginPage: ShokLoginPage

    @Before
    fun setup() {
        loginPage = ShokLoginPage(composeRule)
        profilePage = ShokProfilePage(composeRule)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        loginPage.login(
            TestUsers.oldUser.email,
            TestUsers.oldUser.password
        )
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editButton)
        )

    }



    @Test
    fun shouldGoEditProfile() {
        fun disableAnimations() {
            with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
                executeShellCommand("settings put global transition_animation_scale 0.0")
                executeShellCommand("settings put global window_animation_scale 0.0")
                executeShellCommand("settings put global animator_duration_scale 0.0")
            }
        }
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertIsDisplayed()
        profilePage.navigateToEditProfile()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 9_000L,
            matcher = hasTestTag(Tags.EditProfileScreen.nameInput)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameInput).assertExists()
    }

    @Test
    fun shouldLogOut() {
        fun disableAnimations() {
            with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
                executeShellCommand("settings put global transition_animation_scale 0.0")
                executeShellCommand("settings put global window_animation_scale 0.0")
                executeShellCommand("settings put global animator_duration_scale 0.0")
            }
        }
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertIsDisplayed()
        profilePage.performLogout()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertDoesNotExist()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertExists()
    }
}