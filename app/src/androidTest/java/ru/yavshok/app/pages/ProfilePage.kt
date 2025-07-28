package ru.yavshok.app.pages
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import ru.yavshok.app.Tags
import androidx.test.uiautomator.UiDevice

class ProfilePage(composeTestRule: AndroidComposeTestRule<*, *>)
: BasePage(composeTestRule){
    val nameField = composeTestRule.onNodeWithTag(Tags.ProfileScreen.nameField)
    val statusField = composeTestRule.onNodeWithTag(Tags.ProfileScreen.statusField)
    val logoutButton = composeTestRule.onNodeWithTag(Tags.ProfileScreen.logoutButton)
    val renameButton = composeTestRule.onNodeWithTag(Tags.ProfileScreen.renameButton)

    fun disableAnimations() {
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            executeShellCommand("settings put global transition_animation_scale 0.0")
            executeShellCommand("settings put global window_animation_scale 0.0")
            executeShellCommand("settings put global animator_duration_scale 0.0")
        }
    }


    fun clickLogout(): ProfilePage {

        logoutButton.performClick()
        return this
    }

    fun clickRenameButton(): ProfilePage {
        renameButton.performClick()
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitForNameField() {
        composeTestRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.nameField)
        )
    }





}

