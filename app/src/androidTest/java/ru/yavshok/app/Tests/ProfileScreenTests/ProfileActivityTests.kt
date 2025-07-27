package ru.yavshok.app.Tests.ProfileScreenTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Pages.EditScreenPage
import ru.yavshok.app.Pages.LoginScreenPage
import ru.yavshok.app.Pages.MainScreenPage
import ru.yavshok.app.Pages.ProfileScreenPage
import ru.yavshok.app.Pages.RegisterScreenPage
import ru.yavshok.app.Utils.testData

@RunWith(AndroidJUnit4::class)
class ProfileActivityTests {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var mainScreen: MainScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private lateinit var editScreen: EditScreenPage

    @Before
    fun setUp() {
        mainScreen = MainScreenPage(composeRuleActivity)
        loginScreen = LoginScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)
        editScreen = EditScreenPage(composeRuleActivity)

        performLogin()
    }

    private fun performLogin() {
        mainScreen
            .timeoutFotTitle()
            .clickLoginButton()

        loginScreen
            .timeoutTitle()
            .inputEmail(testData.VALID_EMAIL)
            .inputPassword(testData.VALID_PASSWORD)
            .clickButtonInShock()

        profileScreen
            .timeoutOnProfile()
            .displayedUserName()
    }

    @After
    fun tearDown() {
        try {
            profileScreen
                .clickLogoutButton()
        } catch (e: Exception) {
            composeRuleActivity.activityRule.scenario.recreate()
        }
    }

    @Test
    fun allViewInProfile() {
        profileScreen
            .displayedUserAvatar()
            .displayedUserName()
            .displayedUserStatus()
            .displayedLogout()
            .displayedButtonEdit()
    }

    @Test
    fun shouldNavigateFromProfileScreenLogoutToMainScreen() {
        profileScreen
            .clickLogoutButton()

        mainScreen
            .timeoutFotTitle()
            .displayedTitle()
    }
}