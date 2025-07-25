package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.fixture.YavshokComposeTestFixture
import ru.yavshok.app.fixture.fixture

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class AuthorizationTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = YavshokComposeTestFixture.before()

    @Test
    fun shouldAuthorizeUser() = fixture(composeRule) {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.shouldAuthorizeUser()

        profilePage.waitExists()
        composeRule.waitForIdle()
        loginPage.title.assertDoesNotExist()
        profilePage.header.assertIsDisplayed()
    }
}
