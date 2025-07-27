package ru.yavshok.app

import Pages.ShokMainPage
import Users.TestUsers
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.extensions.TestSetup
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var mainPage: ShokMainPage

    @Before
    fun TestSetup(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin ={},
                viewModel = MainViewModel()
            )
        }
        mainPage = ShokMainPage(composeRule)
    }


    @Test
    fun shouldPlaceHolderExist(){
        mainPage.verifyScreenDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailInput).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.emailInput).assertTextContains("Введите Email")
    }

    @Test
    fun shouldBeInShok(){
        mainPage.verifyScreenDisplayed()
        mainPage.checkEmail(TestUsers.oldUser.email)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 9_000L,
            matcher = hasTestTag(Tags.MainScreen.ShokStatus)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.ShokStatus).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.ShokStatus).assertTextContains("Ты уже в ШОКе")
    }

    @Test
    fun shouldNotBeInShok(){
        mainPage.verifyScreenDisplayed()
        mainPage.checkEmail(TestUsers.newUser.email)
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.NonShokStatus)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.NonShokStatus).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.NonShokStatus).assertTextContains("Ты еще не в ШОКе")
    }


}