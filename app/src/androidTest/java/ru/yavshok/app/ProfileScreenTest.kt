package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.screens.MainScreen
import ru.yavshok.app.screens.ProfileScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreen
    private lateinit var loginScreen: LoginScreen
    private lateinit var profileScreen: ProfileScreen

    @Before
    fun setUp() {
        mainScreen = MainScreen(composeRule)
        loginScreen = LoginScreen(composeRule)
        profileScreen = ProfileScreen(composeRule)

        with(mainScreen) {
            waitToLoad()
            clickLoginButton()
        }

        with(loginScreen) {
            waitToLoad()
            enterEmail("testuser@domain.com")
            enterPassword("123456")
            clickLoginButton()
        }

        profileScreen.waitToLoad()
    }

    @Test
    fun shouldDisplayedAllViewsScreen() {
        with(profileScreen) {
            waitToLoad()
            checkNameLabelIsDisplayed()
            checkStatusLabelIsDisplayed()
            checkLogoutButtonIsDisplayed()
            checkEditButtonIsDisplayed()
        }
    }

    @Test
    fun logoutButtonShouldNavigateToMainScreen() {
        with(profileScreen) {
            checkLogoutButtonIsDisplayed()
            clickLogoutButton()
        }

        mainScreen.waitToLoad()
    }
}