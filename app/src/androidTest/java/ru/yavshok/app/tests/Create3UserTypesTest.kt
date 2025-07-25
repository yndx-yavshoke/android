package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
class Create3UserTypesTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = YavshokComposeTestFixture.before()

    @Test
    fun shouldCreateYoungUser() = fixture(composeRule) {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.goToRegister()

        registerPage.waitExists()
        registerPage.shouldRegisterYoungUser()

        profilePage.waitExists()

        profilePage.status.assertIsDisplayed()
        profilePage.status.assertTextContains(Data.YOUNG_STATUS)
        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.DEFAULT_NAME)
    }

    @Test
    fun shouldCreateAdultUser() = fixture(composeRule) {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.goToRegister()

        registerPage.waitExists()
        registerPage.shouldRegisterAdultUser()

        profilePage.waitExists()

        profilePage.status.assertIsDisplayed()
        profilePage.status.assertTextContains(Data.ADULT_STATUS)
        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.DEFAULT_NAME)
    }

    @Test
    fun shouldCreateOldUser() = fixture(composeRule) {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.goToRegister()

        registerPage.waitExists()
        registerPage.shouldRegisterOldUser()

        profilePage.waitExists()

        profilePage.status.assertIsDisplayed()
        profilePage.status.assertTextContains(Data.OLD_STATUS)
        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.DEFAULT_NAME)
    }
}
