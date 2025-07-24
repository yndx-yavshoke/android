package ru.yavshok.app

import android.nfc.Tag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.uiautomator.UiDevice
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class EditScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var testEmail: String
    private lateinit var testPassword: String

    private lateinit var uiDevice: UiDevice


    @Before
    fun setUp(){
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())


        disableAnimations()

        testEmail = TestDataGenerator.generateRandomEmail()
        testPassword = TestDataGenerator.generateRandomPassword()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 20_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 20_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.registrationButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 20_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput(testPassword)
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("30")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("30")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).performClick()
    }

    private fun disableAnimations() {
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            uiDevice.executeShellCommand("settings put global transition_animation_scale 0.0")
            uiDevice.executeShellCommand("settings put global window_animation_scale 0.0")
            uiDevice.executeShellCommand("settings put global animator_duration_scale 0.0")
        }
    }


    @Test
    fun ShouldShowAllElements(){

        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.titleText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameLabel).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.errorMessage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.saveButtonChanges).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).assertIsDisplayed()

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()

    }

    @Test
    fun ShouldCancelButtonNavigateToProfileScreen(){


        composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).performClick()
        composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()

    }

    @Test
    fun ShouldAvailableNameTextField(){

        composeRule.onNodeWithTag(Tags.EditProfileScreen.saveButtonChanges).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameField).performTextInput("Nekoo")
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameField).assertTextContains("Nekoo")
        composeRule.onNodeWithTag(Tags.EditProfileScreen.saveButtonChanges).assertIsEnabled()

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()

    }







}