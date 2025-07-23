package ru.yavshok.app.Tests.ProfileScreenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Screens.EditScreenPage
import ru.yavshok.app.Screens.LoginScreenPage
import ru.yavshok.app.Screens.MainScreenPage
import ru.yavshok.app.Screens.ProfileScreenPage
import ru.yavshok.app.Screens.RegisterScreenPage
import ru.yavshok.app.Utils.testData

@RunWith(AndroidJUnit4::class)
class ProfileScreenActivityTests {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var editScreen: EditScreenPage

    @Before
    fun setUp(){

        mainScreen = MainScreenPage(composeRuleActivity)
        loginScreen = LoginScreenPage(composeRuleActivity)
        registerScreen = RegisterScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)
        editScreen = EditScreenPage(composeRuleActivity)

        mainScreen
            .timeoutFotTitle()
            .clickLoginButton()
        loginScreen
            .timeoutTitle()
            .inputEmail(testData.VALID_EAMIL)
            .inputPassword(testData.VALID_PASSWORD)
            .clickButtonInShock()
        profileScreen
            .timeoutForUserName()
            .displayedUserName()
    }

    @Test
    fun allViewInProfile(){
        profileScreen
            .displayeduserAvatar()
            .displayedUserName()
            .displayedUserStatus()
            .displayedLogout()
            .displayedButtonEdit()
            .clickLogoutButton()
    }

    @Test
    fun shouldNavigateFromProfileScreenLogoutToMainScreen() {
        profileScreen
            .displayedUserName()
            .displayedLogout()
            .clickLogoutButton()
        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
    }

    @Test
    fun shouldNavigateFromProfileScreenToEditScreen() {
        profileScreen
            .displayedUserName()
            .displayedLogout()
            .clickEditButton()
        editScreen
            .timeoutTitle()
            .displayedTitle()
            .clickButtonCancel()
        profileScreen
            .clickLogoutButton()
    }
}