package ru.yavshok.app

import android.nfc.Tag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
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
class ProfileScreenTest {

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
    }

    private fun disableAnimations() {
        uiDevice.executeShellCommand("settings put global transition_animation_scale 0.0")
        uiDevice.executeShellCommand("settings put global window_animation_scale 0.0")
        uiDevice.executeShellCommand("settings put global animator_duration_scale 0.0")
    }


    @Test
    fun ShouldRedirectToProfilePageAfterSuccessRegistrationWithYoungAge(){



        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("10")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("10")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertTextContains("Молоденький котик")

        uiDevice.pressBack()

    }

    @Test
    fun ShouldRedirectToProfilePageAfterSuccessRegistrationWithMiddleAge(){



        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("40")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("40")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertTextContains("Взрослый котик")

        uiDevice.pressBack()

    }

    @Test
    fun ShouldRedirectToProfilePageAfterSuccessRegistrationWithOldAge(){



        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("90")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("90")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()

        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.ageStatusText).assertTextContains("Старый котик")

        uiDevice.pressBack()

    }

    @After
    fun tearDown() {
        uiDevice.executeShellCommand("settings put global animator_duration_scale 1.0")
    }



}