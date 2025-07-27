package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.modules.MainPage
import ru.yavshok.app.dataEmail

@OptIn(ExperimentalTestApi::class)
class MainPageNavigationTests {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var page : MainPage

    @Before
    fun setup(){
        page = MainPage(composeRule)
    }

    @Test
    fun shouldGoToLoginScreenFromStartState(){
        with(page){
            isVisibleTitle()
            isVisibleLoginButton()
            clickLogin()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldGoToLoginScreenFromSuccessState(){
        with(page){
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail(dataEmail)
            isVisiblechekShokButton()
            isEnableButton()
            clickChekShok()
            isVisibleLoginButton()
            clickLogin()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun shouldGoToLoginScreenFromUnsuccessState(){
        with(page){
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail("tup@mail.ru")
            isVisiblechekShokButton()
            isEnableButton()
            clickChekShok()
            isVisibleLoginButton()
            clickLogin()
        }
        composeRule.waitUntilAtLeastOneExists(timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
    }
}