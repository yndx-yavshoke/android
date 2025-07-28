package ru.yavshok.app.tests.edit_profile

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.ValidUser
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.pages.EditProfilePage
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage


@RunWith(AndroidJUnit4::class)
class EditProfileScreenActivityTest {

    @get:Rule
    var composeRule = createAndroidComposeRule<MainActivity>()


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
            clickEditButton()
        }
        with(editProfilePage){
            allElementsIsDisplayed()
        }
    }

    @After
    fun logout(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        TokenStorage(context).logout()
    }

    @Test
    fun shouldSaveNewNameAfterSaveButtonClick() {
        val name = Faker().name().firstName()
        with(editProfilePage) {
            fillName(name)
            clickSaveButton()
        }
        with(profilePage){
            allElementsIsDisplayed()
            asserNameIsDisplayed(name)
            clickEditButton()
        }
        with(editProfilePage){
            setDefaultName()
        }
    }

    @Test
    fun shouldNotSaveNewNameAfterCancelButtonClick() {
        val name = Faker().name().firstName()
        with(editProfilePage) {
            fillName(name)
            clickCancelButton()
        }
        with(profilePage){
            allElementsIsDisplayed()
            asserNameIsDisplayed(ValidUser.DEFAULT_NAME)
        }
    }
}