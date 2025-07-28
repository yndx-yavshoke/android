package ru.yavshok.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.setupAuth


@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val profileScreen by lazy { ProfileScreenPage(composeRule) }
    private val mainScreen by lazy { MainScreenPage(composeRule) }
    private val loginScreen by lazy { LoginScreenPage(composeRule) }

    @Before
    fun setup() {
        val context = composeRule.activity
        setupAuth.loginUser(context, "valerii.mrm@yandex.ru", "qwerty123")
        mainScreen.waitExistTitle().clickLogin()
        loginScreen.waitExistTitle().login("valerii.mrm@yandex.ru", "qwerty123")
    }

    @Test
    fun shouldDisplayAllUIElementsWithCorrectTextsOnProfileScreen() {
        profileScreen
            .waitUserName()
            .checkAvatarIsDisplayed()
            .checkUserNameText("Janet Murazik")
            .checkAgeStatusText("Взрослый котик")
            .checkLogoutButtonIsDisplayed()
            .checkEditButtonIsDisplayed()
            .checkEditButtonTextIsDisplayed()
    }

    @Test
    fun shouldDisplayCorrectAgeStatusForYoungCat() {
        profileScreen.waitUserName()
            .checkAgeStatusText("Взрослый котик")

        composeRule.onNodeWithTag("main-email-input").assertDoesNotExist()
        profileScreen.checkLogoutButtonIsDisplayed()
    }

    @Test
    fun shouldLogout() {
        profileScreen
            .waitUserName()
            .checkLogoutButtonIsDisplayed()
            .clickLogout()

        mainScreen.waitExistTitle()
    }
}