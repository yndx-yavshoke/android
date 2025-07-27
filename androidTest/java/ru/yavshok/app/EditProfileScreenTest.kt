package ru.yavshok.app

import Pages.ShokEditPage
import Pages.ShokLoginPage
import Pages.ShokMainPage
import Pages.ShokProfilePage
import Users.TestUsers
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class EditProfileTests {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var editPage: ShokEditPage
    private lateinit var profilePage: ShokProfilePage
    private lateinit var loginPage: ShokLoginPage
    private lateinit var mainPage: ShokMainPage

    @Before
    fun TestSetup() {
        profilePage = ShokProfilePage(composeRule)
        editPage = ShokEditPage(composeRule)
        mainPage = ShokMainPage(composeRule)
        loginPage = ShokLoginPage(composeRule)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        mainPage.navigateToLogin()
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
        profilePage.navigateToEditProfile()
        editPage.verifyScreenDisplayed()
    }

    @Test
    fun shouldSuccessNameChange() {
        fun disableAnimations() {
            with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
                executeShellCommand("settings put global transition_animation_scale 0.0")
                executeShellCommand("settings put global window_animation_scale 0.0")
                executeShellCommand("settings put global animator_duration_scale 0.0")
            }
        }
        val newName = "Arisha"
        editPage.saveChanges(Users.TestUsers.newName)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.logoutButton)
        )
        profilePage.navigateToEditProfile()
        editPage.verifyNameFieldContent(newName)
    }

    @Test
    fun shouldShowErrorForLongName() {
        fun disableAnimations() {
            with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
                executeShellCommand("settings put global transition_animation_scale 0.0")
                executeShellCommand("settings put global window_animation_scale 0.0")
                executeShellCommand("settings put global animator_duration_scale 0.0")
            }
        }
        val invalidName = "A".repeat(51)

        //composeRule.onNodeWithTag(Tags.EditProfileScreen.nameInput).performTextInput(invalidName)
        editPage.saveChanges(invalidName)
        composeRule.onNodeWithText("Name must be 50 characters or less").assertExists()
    }



}