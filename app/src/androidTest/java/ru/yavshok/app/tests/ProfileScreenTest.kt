package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.Data
import ru.yavshok.app.fixture.YavshokComposeTestFixture
import ru.yavshok.app.fixture.fixture

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = YavshokComposeTestFixture.before()

    @Test
    fun shouldDisplayHeader() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()
        profilePage.header.assertIsDisplayed()
    }

    @Test
    fun shouldDisplayValidNameAndStatus() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.MY_NAME)
        profilePage.status.assertIsDisplayed()
        profilePage.status.assertTextContains(Data.ADULT_STATUS)
    }

    @Test
    fun shouldLogout() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.logoutIcon.assertIsDisplayed()
        profilePage.logoutIcon.performClick()

        profilePage.header.assertDoesNotExist()
        mainPage.title.assertIsDisplayed()
    }
}
