package ru.yavshok.app.fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import ru.yavshok.app.Tags

class ProfileScreenFixture(private val testRule: ComposeTestRule) {
    private val name = testRule.onNodeWithTag(Tags.ProfileScreen.name)
    private val status = testRule.onNodeWithTag(Tags.ProfileScreen.status)
    private val logoutButton = testRule.onNodeWithTag(Tags.ProfileScreen.exitButton)
    private val editButton = testRule.onNodeWithTag(Tags.ProfileScreen.editButton)
    private val photoGrid = testRule.onNodeWithTag(Tags.ProfileScreen.photoGrid)
    private val photo = testRule.onNodeWithTag(Tags.ProfileScreen.photo)

    fun checkName(): ProfileScreenFixture {
        name.assertIsDisplayed()
        return this
    }

    fun checkStatus(): ProfileScreenFixture {
        status.assertIsDisplayed()
        return this
    }

    fun checkLogoutButton(): ProfileScreenFixture {
        logoutButton.assertIsDisplayed()
        return this
    }

    fun checkEditButton(): ProfileScreenFixture {
        editButton.assertIsDisplayed()
        return this
    }

    fun checkPhotoGrid(): ProfileScreenFixture {
        photoGrid.assertIsDisplayed()
        return this
    }

    fun checkPhoto(): ProfileScreenFixture {
        photo.assertIsDisplayed()
        return this
    }

    fun clickLogoutButton(): ProfileScreenFixture {
        logoutButton.assertIsDisplayed()
        logoutButton.performClick()
        return this
    }

    fun clickEditButton(): ProfileScreenFixture {
        editButton.assertIsDisplayed()
        editButton.performClick()
        return this
    }

}