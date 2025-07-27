package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.EditPage
import ru.yavshok.app.modules.ProfilePage
import ru.yavshok.app.dataEmail
import ru.yavshok.app.dataPassword
import kotlin.concurrent.thread

@OptIn(ExperimentalTestApi::class)
class EditPageTests {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    lateinit var page: EditPage
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
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editButton).performClick()
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.EditScreen.screenTitle))
        page = EditPage(composeRule)
    }

    @Test
    fun shouldGoToProfileScreenAfterClick(){
        with(page){
            isVisibleCancelButton()
            clickCancel()
        }
        val profilePage = ProfilePage(composeRule)
        profilePage.isVisibleUserImg()
    }

    @Test
    fun shouldChangeNameAfterSaveClick(){
        val name = Faker().letterify("???????").toString()
        with(page){
            isVisibleTitle()
            isVisibleNameTextField()
            clearName()
            fillName(name)
            isVisibleSaveButton()
            clickSave()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L, matcher = hasTestTag(Tags.ProfileScreen.nameText))
        val profilePage = ProfilePage(composeRule)
        profilePage.isVisibleNameText()
        profilePage.nameIs(name)
    }

    @Test
    fun shouldDisplayNotEnabledSave(){
        with(page){
            isVisibleTitle()
            isVisibleNameTextField()
            clearName()
            isVisibleSaveButton()
            isSaveNotEnabled()
        }
    }

    @Test
    fun shouldShowErrorWithLongName(){
        val name = Faker().lorem().fixedString(51)
        with(page){
            isVisibleTitle()
            isVisibleNameTextField()
            clearName()
            fillName(name)
            isVisibleSaveButton()
            clickSave()
            isVisibleFieldError()
            thread { 900000 }
            errorIs("Name must be 50 characters or less")
        }
    }
}