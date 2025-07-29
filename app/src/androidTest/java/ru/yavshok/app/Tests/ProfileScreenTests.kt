package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Screens.*
import ru.yavshok.app.Utils.DataGenerator
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenTests {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var editProfileScreen: EditProfileScreenPage

    @Before
    fun setUp() {

        mainScreen = MainScreenPage(composeRule)
        loginScreen = LoginScreenPage(composeRule)
        registerScreen = RegisterScreenPage(composeRule)
        profileScreen = ProfileScreenPage(composeRule)
        editProfileScreen = EditProfileScreenPage(composeRule)

        with(mainScreen) {
            waitExists()
            clickLoginButton()
        }
        with(loginScreen) {
            waitExists()
            enterEmail(DataGenerator.TestUser.existingUserEmail)
            enterPassword(DataGenerator.TestUser.existingUserPassword)
            clickLoginButton()
        }
        with(profileScreen) {
            waitExists()
        }
    }

    @Test
    fun shouldDisplayAllProfileElements() {
        with(profileScreen) {
            assertIsDisplayedUserName()
            assertIsDisplayedUserStatus()
            assertIsDisplayedButtonEdit()
            assertIsDisplayedLogout()
        }
    }

    @Test
    fun shouldNavigateToEditProfile() {
        with(profileScreen) {
            assertIsDisplayedButtonEdit()
            clickEditButton()
        }
    }

    @Test
    fun shouldLogoutSuccessfully() {
        with(profileScreen) {
            assertIsDisplayedLogout()
            clickLogoutButton()
        }
    }
}