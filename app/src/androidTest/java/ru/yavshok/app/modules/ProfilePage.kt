package ru.yavshok.app.modules

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfilePage (private val rule: ComposeTestRule) {
    private val nameText = Tags.ProfileScreen.nameText
    private val statusText = Tags.ProfileScreen.statusText
    private val userImg = Tags.ProfileScreen.userImg
    private val logoutButton = Tags.ProfileScreen.logoutButton
    private val editButton = Tags.ProfileScreen.editButton

    fun isVisibleNameText() {
        rule.onNodeWithTag(nameText).assertIsDisplayed()
    }

    fun isVisibleStatusText() {
        rule.onNodeWithTag(statusText).assertIsDisplayed()
    }

    fun isVisibleUserImg() {
        rule.onNodeWithTag(userImg).assertIsDisplayed()
    }

    fun isVisibleLogoutButton() {
        rule.onNodeWithTag(logoutButton).assertIsDisplayed()
    }

    fun isVisibleEditButton() {
        rule.onNodeWithTag(editButton).assertIsDisplayed()
    }

    fun clickLogoutButton(){
        rule.onNodeWithTag(logoutButton).performClick()
    }

    fun clickEditButton(){
        rule.onNodeWithTag(editButton).performClick()
    }

    fun nameIs(value: String){
        rule.onNodeWithTag(nameText).assertTextEquals(value)
    }

    fun statusIs(value: String){
        rule.onNodeWithTag(statusText).assertTextEquals(value)
    }
}