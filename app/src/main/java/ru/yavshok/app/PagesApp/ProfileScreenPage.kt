package ru.yavshok.app.screens.profile

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import ru.yavshok.app.testdata.TestConfig

class ProfileScreenPage(private val composeTestRule: ComposeTestRule) {

    // Основные элементы
    private val profileName = composeTestRule.onNodeWithText(TestConfig.Profile.PROFILE_NAME)
    private val profileSubtitle = composeTestRule.onNodeWithText(TestConfig.Profile.PROFILE_SUBTITLE)
    private val postsCount = composeTestRule.onNodeWithText(TestConfig.Profile.POSTS_COUNT)
    private val followersCount = composeTestRule.onNodeWithText(TestConfig.Profile.FOLLOWERS_COUNT)
    private val likesCount = composeTestRule.onNodeWithText(TestConfig.Profile.LIKES_COUNT)
    private val editProfileButton = composeTestRule.onNodeWithText(TestConfig.Profile.EDIT_PROFILE_BUTTON)

    // Методы проверки
    fun verifyAllElementsVisible() {
        profileName.assertExists()
        profileSubtitle.assertExists()
        postsCount.assertExists()
        followersCount.assertExists()
        likesCount.assertExists()
        editProfileButton.assertExists()
    }

    fun verifyStatsCorrect() {
        postsCount.assertTextEquals(TestConfig.Profile.POSTS_COUNT)
        followersCount.assertTextEquals(TestConfig.Profile.FOLLOWERS_COUNT)
        likesCount.assertTextEquals(TestConfig.Profile.LIKES_COUNT)
    }

    fun clickEditProfile() {
        editProfileButton.performClick()
    }

    
    
}