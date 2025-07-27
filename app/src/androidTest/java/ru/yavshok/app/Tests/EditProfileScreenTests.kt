package ru.yavshok.app.Tests.EditScreenTests

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

@RunWith(AndroidJUnit4::class)
class EditScreenActivityTests {
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
            clickEditButton()
        }
        with(editProfileScreen) {
            waitExists()
        }
    }

    @Test
    fun allViewInEditPage() {
        with(editProfileScreen) {
            waitExists()
            assertNameLineIsDisplayed()
            assertNameFieldIsDisplayed()
            assertSaveButtonIsDisplayed()
            assertCancelButtonIsDisplayed()
            clickCancelButton()
        }
        with(profileScreen) {
            waitExists()
            clickLogoutButton()
        }
    }


    @Test
    fun renameProfileInEditPage() {
        val listName = listOf(
            "Cat",
            "Meow",
            "Мяу"
        )
        for (name in listName) {
            with(editProfileScreen) {
                waitExists()
                assertNameLineIsDisplayed()
                assertNameFieldIsDisplayed()
                clearNameField()
                inputName(name)
                assertSaveButtonIsDisplayed()
                assertCancelButtonIsDisplayed()
                clickSaveButton()
                clickCancelButton()
            }
            with(profileScreen) {
                waitExists()
                nameLineHasName(name)
                clickEditButton()
            }
        }
        with(editProfileScreen) {
            waitExists()
            clickCancelButton()
        }
        with(profileScreen) {
            waitExists()
            clickLogoutButton()
        }
    }
}
