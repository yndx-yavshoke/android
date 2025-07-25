package ru.yavshok.app.tests.editScreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4


import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.fixtures.EditScreenPageObj
import ru.yavshok.app.fixtures.LoginScreenPageObj
import ru.yavshok.app.fixtures.MainScreenPageObj
import ru.yavshok.app.fixtures.ProfileScreenPageObj
import ru.yavshok.app.utils.DisabledSystemAnimations
import ru.yavshok.app.utils.EnvData
import ru.yavshok.app.utils.FakerDataGenerator


@RunWith(AndroidJUnit4::class)
class EditScreenTestActivity: DisabledSystemAnimations() {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun shouldChangeAndSaveUserName() {
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
            .clickOnEditButton()

        EditScreenPageObj(composeRule)
            .waitForScreen()
            .clearNameField()
            .inputName(FakerDataGenerator.randomName())
            .clickOnSaveButton()

        ProfileScreenPageObj(composeRule)
            .waitForScreen()

    }

    @Test
    fun shouldCancelUserNameChange() {
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
            .clickOnEditButton()

        EditScreenPageObj(composeRule)
            .waitForScreen()
            .clickOnCancelButton()

        ProfileScreenPageObj(composeRule)
            .waitForScreen()

    }

    @Test
    fun shouldLockSaveButtonWhenNameFieldIsEmpty() {
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
            .clickOnEditButton()

        EditScreenPageObj(composeRule)
            .waitForScreen()
            .clearNameField()
            .clickOnSaveButton()
            .assertScreenIsVisible()

    }

}