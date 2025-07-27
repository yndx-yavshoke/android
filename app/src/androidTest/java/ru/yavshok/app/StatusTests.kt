package ru.yavshok.app

import android.nfc.Tag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.github.javafaker.Faker
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.modules.ProfilePage
import ru.yavshok.app.modules.RegisterPage

@OptIn(ExperimentalTestApi::class)
@RunWith(Parameterized::class)
class StatusTests(
    private val age: Int,
    private val expectedStatus: String,
    private val email: String,
    private val password: String
)  {
    @get: Rule
    var composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var page : RegisterPage

    @Before
    fun setup(){
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            executeShellCommand("settings put global transition_animation_scale 0.0")
            executeShellCommand("settings put global window_animation_scale 0.0")
            executeShellCommand("settings put global animator_duration_scale 0.0")
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 8000L, matcher = hasTestTag(Tags.MainScreen.loginButton))
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.RegisterScreen.screenTitle))
        page = RegisterPage(composeRule)
    }

    @After
    fun recreate() {
        TokenStorage(composeRule.activity).logout()
        composeRule.activityRule.scenario.recreate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "age = {0}, expectedStatus = {1}, email = {2}, password = {3}")
        fun data() = listOf(
            arrayOf(0, "Молоденький котик", Faker().internet().emailAddress(), Faker().internet().password()),
            arrayOf(21, "Молоденький котик", Faker().internet().emailAddress(), Faker().internet().password()),
            arrayOf(22, "Взрослый котик", Faker().internet().emailAddress(), Faker().internet().password()),
            arrayOf(68, "Взрослый котик", Faker().internet().emailAddress(), Faker().internet().password()),
            arrayOf(69, "Старый котик", Faker().internet().emailAddress(), Faker().internet().password()),
            arrayOf(99, "Старый котик", Faker().internet().emailAddress(), Faker().internet().password())
        )
    }


    @Test
    fun shouldGetCorrectStatusByAge() {
        with(page) {
            isVisibleTitle()
            isVisibleEmailTextField()
            isVisiblePasswordTextField()
            isVisibleAgeTextField()
            fillEmail(email)
            fillPassword(password)
            fillAge(age.toString())
            isVisibleRegisterButton()
            clickRegister()
        }

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.ProfileScreen.nameText)
        )

        val profilePage = ProfilePage(composeRule)
        with(profilePage) {
            isVisibleStatusText()
            statusIs(expectedStatus)
        }
    }
}