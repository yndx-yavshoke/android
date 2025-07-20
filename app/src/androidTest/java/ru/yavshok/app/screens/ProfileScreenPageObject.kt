package ru.yavshok.app.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

class ProfileScreenPageObject(private val composeTestRule: AndroidComposeTestRule<*, *>) {
    public val catName = composeTestRule.onNodeWithTag("profile_screen.cat_name")
    public val catAge = composeTestRule.onNodeWithTag("profile_screen.cat_age",)
    public val logoutButton = composeTestRule.onNodeWithTag("profile_screen.logout_button")
    public val editButton = composeTestRule.onNodeWithTag("profile_screen.edit_button")

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