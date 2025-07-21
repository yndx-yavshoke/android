package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreenPageObject(composeTestRule: AndroidComposeTestRule<*, *>) {
    val catName = composeTestRule.onNodeWithTag(Tags.ProfileScreen.catName)
    val catAge = composeTestRule.onNodeWithTag(Tags.ProfileScreen.catAge)
    val logoutButton = composeTestRule.onNodeWithTag(Tags.ProfileScreen.logoutButton)
    val editButton = composeTestRule.onNodeWithTag(Tags.ProfileScreen.editButton)

    fun clickLogout(): ProfileScreenPageObject {
        logoutButton.assertIsDisplayed()
        logoutButton.performClick()
        return this
    }

    fun clickEdit(): ProfileScreenPageObject {
        editButton.assertIsDisplayed()
        editButton.performClick()
        return this
    }
}