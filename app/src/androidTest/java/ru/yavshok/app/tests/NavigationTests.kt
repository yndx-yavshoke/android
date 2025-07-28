package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.MainActivity
import ru.yavshok.app.screens.EditProfileScreen
import ru.yavshok.app.screens.LoginScreen
import ru.yavshok.app.screens.MainScreen
import ru.yavshok.app.screens.ProfileScreen
import ru.yavshok.app.screens.RegisterScreen

class NavigationTests {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val mainScreen = MainScreen(composeRule)
    val loginScreen = LoginScreen(composeRule)
    val registerScreen = RegisterScreen(composeRule)
    val profileScreen = ProfileScreen(composeRule)
    val editProfileScreen = EditProfileScreen(composeRule)

    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        mainScreen.waitExists()
        mainScreen.assertScreenDisplayed()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldNavigateLoginScreenToMainScreen() {
        mainScreen.waitExists()
        mainScreen.assertScreenDisplayed()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.assertScreenDisplayed()
        loginScreen.clickOnBack()

        mainScreen.waitExists()
        mainScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldNavigateLoginScreenToRegisterScreen() {
        mainScreen.waitExists()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.assertScreenDisplayed()
        loginScreen.clickOnRegister()

        registerScreen.waitExists()
        registerScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldNavigateRegisterScreenToLoginScreen() {
        mainScreen.waitExists()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.clickOnRegister()

        registerScreen.waitExists()
        registerScreen.assertScreenDisplayed()
        registerScreen.clickOnBack()

        loginScreen.waitExists()
        loginScreen.assertScreenDisplayed()
    }

    @Test
    fun shouldNavigateFromLoginScreenToProfileScreenWithRegisterUser() {
        mainScreen.waitExists()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.logInWithRegisterUser()

        profileScreen.waitExists()
        profileScreen.assertScreenDisplayed()
    }

    //аналогично не могу перейти
    @Test
    fun shouldNavigateFromEditProfileScreenToProfileScreen() {
        mainScreen.waitExists()
        mainScreen.clickOnLogin()

        loginScreen.waitExists()
        loginScreen.logInWithRegisterUser()

        profileScreen.waitExists()
        profileScreen.clickOnEditProfile()

        editProfileScreen.waitExists()
        editProfileScreen.clickOnBack()

        profileScreen.waitExists()
        profileScreen.assertScreenDisplayed()
    }
}