package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import ru.yavshok.app.Tags
import kotlin.math.log


class ProfilePage(composeRule: ComposeTestRule) : BasePage(composeRule) {
    private val name = Tags.ProfileScreen.nameText
    private val subtitle = Tags.ProfileScreen.subtitleText
    private val logoutButton = Tags.ProfileScreen.logoutButton
    private val editButton = Tags.ProfileScreen.editButton

    override val nodeIsDisplayed: MutableList<String> = mutableListOf(
        name,
        subtitle,
        logoutButton,
        editButton
    )


    fun clickEditButton(){
        composeRule.onNodeWithTag(editButton).performClick()
    }

    fun clickLogoutButton(){
        composeRule.onNodeWithTag(logoutButton).performClick()
    }

    fun asserNameIsDisplayed(username: String){
        composeRule.onNodeWithTag(name).assertIsDisplayed()
        composeRule.onNodeWithText(username).assertIsDisplayed()
    }
}
