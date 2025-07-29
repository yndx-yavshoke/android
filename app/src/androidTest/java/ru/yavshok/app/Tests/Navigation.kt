package ru.yavshok.app.Tests.NavigationTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Screens.*
import ru.yavshok.app.Utils.DataGenerator

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationAllScreensTests {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var editScreen: EditProfileScreenPage

    @Before
    fun setUp() {
        mainScreen = MainScreenPage(composeRule)
        loginScreen = LoginScreenPage(composeRule)
        registerScreen = RegisterScreenPage(composeRule)
        profileScreen = ProfileScreenPage(composeRule)
        editScreen = EditProfileScreenPage(composeRule)
    }


    @Test
    fun fromMainToLogin() {
        with(mainScreen) {
            waitExists()
            clickLoginButton()
        }
        loginScreen.waitExists()
    }

    @Test
    fun fromLoginToMain() {
        with(mainScreen) {
            waitExists()
            clickLoginButton()
        }
        with(loginScreen) {
            waitExists()
            clickButtonBack()
        }
        mainScreen.waitExists()
    }

    @Test
    fun fromLoginToRegister() {
        with(mainScreen) {
            waitExists()
            clickLoginButton()
        }
        with(loginScreen) {
            waitExists()
            clickRegisterButton()
        }
        registerScreen.waitExists()
    }

    @Test
    fun fromRegisterToLogin() {
        with(mainScreen) {
            waitExists()
            clickLoginButton()
        }
        with(loginScreen) {
            waitExists()
            clickRegisterButton()
        }
        with(registerScreen) {
            waitExists()
            clickBackButton()
        }
        loginScreen.waitExists()
    }

    @Test
    fun successfulLoginToProfile() {
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
            clickLogoutButton()
        }
    }

    @Test
    fun logoutToLogin() {
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
            clickLogoutButton()
        }
        mainScreen.waitExists()
    }

    @Test
    fun fromProfileToEditAndBackCancel() {
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
            clickEditButton()
        }
        with(editScreen) {
            waitExists()
            clickCancelButton()
        }
        with(profileScreen) {
            waitExists()
            clickLogoutButton()
        }
    }
}
