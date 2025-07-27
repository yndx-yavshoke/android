package ru.yavshok.app.Tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Screens.EditProfileScreenPage
import ru.yavshok.app.Screens.LoginScreenPage
import ru.yavshok.app.Screens.MainScreenPage
import ru.yavshok.app.Screens.ProfileScreenPage
import ru.yavshok.app.Screens.RegisterScreenPage
import ru.yavshok.app.Utils.DataGenerator


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenStatusTests {

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
    }


    @Test
    fun checkStatusYoung() {

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
            enterEmail(DataGenerator.FakeUser.email())
            enterPassword(DataGenerator.FakeUser.password())
            enterAge(DataGenerator.FakeUser.youngAge())
            clickRegisterButton()
        }
        with(profileScreen) {
            waitExists()
            assertStatusTextContains(DataGenerator.UserStatus.YOUNG_STATUS)
            clickLogoutButton()
        }
    }

    @Test
    fun checkStatusAdult() {

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
            enterEmail(DataGenerator.FakeUser.email())
            enterPassword(DataGenerator.FakeUser.password())
            enterAge(DataGenerator.FakeUser.adultAge())
            clickRegisterButton()
        }
        with(profileScreen) {
            waitExists()
            assertStatusTextContains(DataGenerator.UserStatus.ADULT_STATUS)
            clickLogoutButton()
        }
    }

    @Test
    fun checkStatusOld() {

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
            enterEmail(DataGenerator.FakeUser.email())
            enterPassword(DataGenerator.FakeUser.password())
            enterAge(DataGenerator.FakeUser.oldAge())
            clickRegisterButton()
        }
        with(profileScreen) {
            waitExists()
            assertStatusTextContains(DataGenerator.UserStatus.OLD_STATUS)
            clickLogoutButton()
        }
    }
}