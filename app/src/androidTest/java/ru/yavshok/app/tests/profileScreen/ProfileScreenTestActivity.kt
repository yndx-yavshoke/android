package ru.yavshok.app.tests.profileScreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.fixtures.LoginScreenPageObj
import ru.yavshok.app.fixtures.MainScreenPageObj
import ru.yavshok.app.fixtures.ProfileScreenPageObj
import ru.yavshok.app.fixtures.RegisterScreenPageObj
import ru.yavshok.app.utils.DisabledSystemAnimations
import ru.yavshok.app.utils.EnvData
import ru.yavshok.app.utils.FakerDataGenerator


@RunWith(AndroidJUnit4::class)
class ProfileScreenTestActivity: DisabledSystemAnimations() {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldLoginAndOpenProfileThenLogout() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToLoginButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(EnvData.email)
            .inputPassword(EnvData.password)
            .clickOnLoginButton()

        ProfileScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnLogoutButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()

    }

    @Test
    fun shouldRegisterAndOpenProfile() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToLoginButton()

        LoginScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnToRegisterButton()

        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .inputPassword(FakerDataGenerator.randomPassword())
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()

        ProfileScreenPageObj(composeRule)
            .waitForScreen()

    }

}
