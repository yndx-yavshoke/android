package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage


@RunWith(AndroidJUnit4::class)
class ProfileTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val profilePage = ProfilePage(composeRule)

    @Before
    fun setup() {

        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        loginPage.waitForTitle()
        profilePage.disableAnimations()

    }
    @Test

    fun testStatusYoungCat() {

        val userEmail = Constants.YOUNG_EMAIL
        val userPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText("Молоденький котик").assertIsDisplayed()
    }

    @Test

    fun testStatusOldCat() {

        val userEmail = Constants.OLD_EMAIL
        val userPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText("Старый котик").assertIsDisplayed()
    }

    @Test

    fun navigateFromProfileScreenToMainScreen() {
        val userEmail = Constants.REGISTER_EMAIL
        val userPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        profilePage.clickLogout()
        composeRule.onNodeWithText("Я в ШОКе").assertIsDisplayed()
    }


    @Test
    fun navigateFromProfileScreenToRenameScreen() {
        val userEmail = Constants.REGISTER_EMAIL
        val userPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        profilePage.clickRenameButton()

        composeRule.onNodeWithText("Save Changes").assertIsDisplayed()
    }

}