package ru.yavshok.app.modules

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import ru.yavshok.app.Tags

class MainPage (private val rule: ComposeTestRule){
    private val title = Tags.MainScreen.screenTitle
    private val emailInput = Tags.MainScreen.emailTextField
    private val checkShokButton = Tags.MainScreen.checkShokButton
    private val loginButton = Tags.MainScreen.loginButton
    private val notInShokText = Tags.MainScreen.notInShokText
    private val inShokText = Tags.MainScreen.inShokText
    private val inShokGif = Tags.MainScreen.inShokGif

    fun isVisibleTitle() {
        rule.onNodeWithTag(title).assertIsDisplayed()
    }

    fun isVisibleEmailInput(){
        rule.onNodeWithTag(emailInput).assertIsDisplayed()
    }

    fun isVisiblechekShokButton(){
        rule.onNodeWithTag(checkShokButton).assertIsDisplayed()
    }

    fun isVisibleLoginButton(){
        rule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    fun isVisibleNotInShokText() {
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodesWithTag(notInShokText).fetchSemanticsNodes().isNotEmpty()
        }
        rule.onNodeWithTag(notInShokText).assertIsDisplayed()
    }

    fun isVisibleInShokText(){
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodesWithTag(inShokText).fetchSemanticsNodes().isNotEmpty()
        }
        rule.onNodeWithTag(inShokText).assertIsDisplayed()
    }

    fun isVisibleInShokGif(){
        rule.onNodeWithTag(inShokGif).assertIsDisplayed()
    }

    fun clickLogin(){
        rule.onNodeWithTag(loginButton).performClick()
    }

    fun clickChekShok(){
        rule.onNodeWithTag(checkShokButton).performClick()
    }

    fun isEnableButton(){
        rule.onNodeWithTag(checkShokButton).assertIsEnabled()
    }

    fun isNotEnableButton(){
        rule.onNodeWithTag(checkShokButton).assertIsNotEnabled()
    }

    fun fillEmail(email: String){
        rule.onNodeWithTag(emailInput).performTextInput(email)
    }

    fun emailIs(value: String){
        rule.onNodeWithTag(emailInput).assertTextEquals(value)
    }

}