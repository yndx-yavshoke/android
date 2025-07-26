package ru.yavshok.app.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import ru.yavshok.app.Tags
import androidx.test.uiautomator.UiDevice

class ProfileScreenPageObject(private val testRule: ComposeTestRule) {
    val catName = testRule.onNodeWithTag(Tags.ProfileScreen.catName)
    val catAge = testRule.onNodeWithTag(Tags.ProfileScreen.catAge)
    val logoutButton = testRule.onNodeWithTag(Tags.ProfileScreen.logoutButton)
    val editButton = testRule.onNodeWithTag(Tags.ProfileScreen.editButton)

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

    @OptIn(ExperimentalTestApi::class)
    fun waitCatName(): ProfileScreenPageObject {
        testRule.waitUntilAtLeastOneExists(
            hasTestTag(Tags.ProfileScreen.catName),
            5000
        )
        return this
    }

    fun disableAnimations() {
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            executeShellCommand("settings put global transition_animation_scale 0.0")
            executeShellCommand("settings put global window_animation_scale 0.0")
            executeShellCommand("settings put global animator_duration_scale 0.0")
        }
    }
}