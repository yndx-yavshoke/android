package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.ProfilePage
import androidx.test.uiautomator.UiDevice
import ru.yavshok.app.modules.EditPage
import ru.yavshok.app.modules.MainPage
import ru.yavshok.app.dataEmail
import ru.yavshok.app.dataPassword

@OptIn(ExperimentalTestApi::class)
class ProfilePageTests {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var page : ProfilePage

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
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput(dataEmail)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).performTextInput(dataPassword)
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.ProfileScreen.nameText))
        page = ProfilePage(composeRule)
    }

    @Test
    fun shouldShowUserInfo(){
        with(page){
            isVisibleUserImg()
            isVisibleNameText()
            isVisibleStatusText()
        }
    }

    @Test
    fun shouldGoToEditScreenAfterClick(){
        with(page){
            isVisibleEditButton()
            clickEditButton()
        }
        val editPage = EditPage(composeRule)
        editPage.isVisibleTitle()
    }

    @Test
    fun shouldGoToMainScreenAfterClick(){
        with(page){
            isVisibleEditButton()
            clickLogoutButton()
        }
        val mainPage = MainPage(composeRule)
        mainPage.isVisibleTitle()
    }
}