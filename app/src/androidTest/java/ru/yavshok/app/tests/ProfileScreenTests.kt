package ru.yavshok.app.tests

import android.util.Log
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.MainActivity
import ru.yavshok.app.screens.EditProfileScreen
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.screens.MainScreen
import ru.yavshok.app.screens.ProfileScreen
import utils.User

class ProfileScreenTests {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val mainScreen = MainScreen(composeRule)
    val loginScreen = LoginScreen(composeRule)
    val profileScreen = ProfileScreen(composeRule)
    val editProfileScreen = EditProfileScreen(composeRule)

    @Before
    fun setup() {
        mainScreen.waitExists()
        mainScreen.clickOnLogin()
        loginScreen.waitExists()
        loginScreen.logInWithRegisterUser()
        profileScreen.waitExists()
        profileScreen.assertScreenDisplayed()
    }

    @After
    fun logOut() {
        profileScreen.clickOnLogOut()
    }


    //тест просто зависает при переходе на страницу профиля
    //если вы знаете как это пофиксить, я буду рада услышать
    @Test
    fun shouldChangeNameOnEditProfileName() {
        val user = User.generateRandomUser()

        profileScreen.clickOnEditProfile()

        editProfileScreen.waitExists()
        editProfileScreen.assertScreenDisplayed()

        editProfileScreen.typeName(user.name)
        editProfileScreen.clickOnSave()
        editProfileScreen.waitUntilButtonShowsSaveChanges()

        profileScreen.assertNameEqualChangedName(user.name)
    }
}