package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage
import ru.yavshok.app.pages.EditProfilePage

@RunWith(AndroidJUnit4::class)
class EditProfileTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val profilePage = ProfilePage(composeRule)
    private val editProfilePage = EditProfilePage(composeRule)


    @Before
    fun setup() {

        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        loginPage.waitForTitle()
        val userEmail = Constants.REGISTER_EMAIL
        val userPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        loginPage.writeEmailPassword(userEmail, userPassword)
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()

        profilePage.clickRenameButton()
        editProfilePage.waitForTitle()

    }

    @Test
    fun testRenameUser() {

        val newName = FakeDataGenerator.generateName()

        editProfilePage.waitForTitle()
        editProfilePage.writeNewName(newName)
        editProfilePage.clickSaveChangesButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText(newName).assertIsDisplayed()
    }


    @Test
    fun testVeryLongName() {

        val newName = FakeDataGenerator.generateVeryLongName()

        editProfilePage.waitForTitle()

        editProfilePage.writeNewName(newName)
        editProfilePage.clickSaveChangesButton()
        editProfilePage.errorText.assertIsDisplayed()
    }

    @Test
    fun testNullName() {

        val newName = ""

        editProfilePage.waitForTitle()
        editProfilePage.writeNewName(newName)

        editProfilePage.clickSaveChangesButton()
        editProfilePage.saveChangesButton.assertIsNotEnabled()
    }

    @Test
    fun testNotSavesNewName() {

        val newName = FakeDataGenerator.generateName()

        editProfilePage.waitForTitle()
        editProfilePage.writeNewName(newName)
        editProfilePage.clickCancelButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText(newName).assertDoesNotExist()
    }

}