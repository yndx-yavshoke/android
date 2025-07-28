package ru.yavshok.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.screens.EditProfileScreenPage
import ru.yavshok.app.fixtures.screens.LoginScreenPage
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.setupAuth

@RunWith(AndroidJUnit4::class)
class EditProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private val mainScreen by lazy { MainScreenPage(composeRule) }
    private val loginScreen by lazy { LoginScreenPage(composeRule) }
    private val profileScreen by lazy { ProfileScreenPage(composeRule) }
    private val editProfileScreen by lazy { EditProfileScreenPage(composeRule) }

    @Before
    fun setup() {
        val context = composeRule.activity
        setupAuth.loginUser(context, "valerii.mrm@yandex.ru", "qwerty123")
        mainScreen.waitExistTitle().clickLogin()
        loginScreen.waitExistTitle().login("valerii.mrm@yandex.ru", "qwerty123")
    }

    @Test
    fun shouldDisplayAllUIElementsWithCorrectTextsOnEditProfileScreen() {
        profileScreen.waitUserName().waitEditButtonIsClickable().clickEdit()
        editProfileScreen.waitExistTitle()
        editProfileScreen
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkNameTitleTextIsDisplayed()
            .checkNameFieldIsDisplayed()
            .checkSaveButtonIsDisplayed()
            .checkCancelButtonIsDisplayed()
    }

    @Test
    fun shouldChangeUserNameSuccessfully() {
        val newName = "Тестировщик"
        editProfileScreen
            .checkNameFieldIsDisplayed()
            .typeName(newName)
            .waitSaveButtonWithText()
            .clickSave()
            .clickCancel()
        profileScreen
            .waitUserName().waitEditButtonIsClickable()
            .checkUserNameText(newName)
    }
}