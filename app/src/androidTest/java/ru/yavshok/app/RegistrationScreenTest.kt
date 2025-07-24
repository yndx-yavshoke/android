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
class RegistrationScreenTest {

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
    }

    private fun disableAnimations() {
        uiDevice.executeShellCommand("settings put global transition_animation_scale 0.0")
        uiDevice.executeShellCommand("settings put global window_animation_scale 0.0")
        uiDevice.executeShellCommand("settings put global animator_duration_scale 0.0")
    }

    @Test
    fun ShouldDisplayErrorAfterFillEmailWithoutDomen(){

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput("email@.com")
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertTextContains("email@.com")
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput(testPassword)
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("20")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("20")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()
        try {
            composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertExists()
            true
        } catch (e: AssertionError) {
            println("Ошибка: Не удалось найти ошибку  (${Tags.RegisterScreen.errorTextMessage})")
            false
        }
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertIsDisplayed()

    }

    @Test
    fun ShouldDisplayErrorAfterFillPasswordBelowSixLenght(){

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput("1")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("20")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("20")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()
        composeRule.waitUntil(1000) {
            try {
                composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertExists()
                true
            } catch (e: AssertionError) {
                println("Ошибка: Не удалось найти ошибку (${Tags.RegisterScreen.errorTextMessage})")
                false
            }
        }
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertIsDisplayed()

    }

    @Test
    fun ShouldDisplayErrorAfterFillAgeBelowZeroAge(){

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput(testPassword)
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("-1")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("-1")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()
        composeRule.waitUntil(1000) {
            try {
                composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertExists()
                true
            } catch (e: AssertionError) {
                println("Ошибка: Не удалось найти ошибку (${Tags.RegisterScreen.errorTextMessage})")
                false
            }
        }
        composeRule.onNodeWithTag(Tags.RegisterScreen.errorTextMessage).assertIsDisplayed()

    }

    @Test
    fun ShouldRedirectToProfilePageAfterSuccessRegistration(){


        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertIsDisplayed()

        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).performTextInput(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.emailField).assertTextContains(testEmail)
        composeRule.onNodeWithTag(Tags.RegisterScreen.passwordField).performTextInput(testPassword)
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).performTextInput("20")
        composeRule.onNodeWithTag(Tags.RegisterScreen.ageField).assertTextContains("20")

        composeRule.onNodeWithTag(Tags.RegisterScreen.registrationButton).performClick()
//        composeRule.waitUntil(1000) {
//            try {
//                composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertExists()
//                true
//            } catch (e: AssertionError) {
//                println("Ошибка: Не удалось найти имя профиля(${Tags.RegisterScreen.errorTextMessage})")
//                false
//            }
//        }

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.ProfileScreen.nameText)
        )

        composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()

        uiDevice.pressBack()

    }

    @After
    fun tearDown() {
        // Можно включить анимации обратно при необходимости
        uiDevice.executeShellCommand("settings put global animator_duration_scale 1.0")
    }



}