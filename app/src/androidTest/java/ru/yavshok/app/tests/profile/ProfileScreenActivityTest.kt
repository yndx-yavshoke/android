package ru.yavshok.app.tests.profile

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.EditProfilePage
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage


@RunWith(AndroidJUnit4::class)
class ProfileScreenActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainPage: MainPage
    private lateinit var loginPage: LoginPage
    private lateinit var profilePage: ProfilePage
    private lateinit var editProfilePage: EditProfilePage

    @Before
    fun setUp(){
        mainPage = MainPage(composeRule)
        loginPage = LoginPage(composeRule)
        profilePage = ProfilePage(composeRule)
        editProfilePage = EditProfilePage(composeRule)

        with(mainPage){
            allElementsIsDisplayed()
            clickToLoginButton()
        }
        with(loginPage){
            allElementsIsDisplayed()
            loginUser()
        }
        with(profilePage){
            allElementsIsDisplayed()
        }
    }

    @Test
    fun shouldRedirectToMainAfterClickLogoutButton() {
        with(profilePage){
            clickLogoutButton()
        }
        with(mainPage){
            allElementsIsDisplayed()
        }
    }

    @Test
    fun shouldRedirectToEditProfileAfterClickEditButton() {
        with(profilePage){
            clickEditButton()
        }
        with(editProfilePage){
            allElementsIsDisplayed()
        }

    }
}