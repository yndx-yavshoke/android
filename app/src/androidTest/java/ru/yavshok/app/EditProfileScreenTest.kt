package ru.yavshok.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.fixtures.screens.EditProfileScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.TestData
import ru.yavshok.app.utils.TestUtils

@RunWith(AndroidJUnit4::class)
class EditProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private val profileScreen by lazy { ProfileScreenPage(composeRule) }
    private val editProfileScreen by lazy { EditProfileScreenPage(composeRule) }
    private lateinit var oldName: String

    private fun navigateToEditProfile() {
        profileScreen.waitUserName()
        oldName = profileScreen.getUserName()
        profileScreen.waitEditButton().clickEdit()
    }

    @Before
    fun setup() {
        TestUtils.disableAnimations()
        val context = composeRule.activity
        TokenStorage(context).logout()

        TestUtils.loginUser(context, TestData.EXISTING_EMAIL_FOR_EDIT, TestData.VALID_PASSWORD_FOR_EDIT)
        TestUtils.launchMainActivityInTestMode()

        navigateToEditProfile()
    }

    @Test
    @DisplayName("Редактирование профиля: Проверка отображения всех UI-элементов")
    fun shouldDisplayAllUIElementsWithCorrectTextsOnEditProfileScreen() {
        editProfileScreen
            .waitExistTitle()
            .checkTitleIsDisplayed()
            .checkTitleTextIsDisplayed()
            .checkNameTitleTextIsDisplayed()
            .checkNameFieldIsDisplayed()
            .checkSaveButtonIsDisplayed()
            .checkCancelButtonIsDisplayed()
    }

    @Test
    @DisplayName("Редактирование профиля: Успешная смена имени пользователя")
    fun shouldChangeUserNameSuccessfully() {
        val newName = TestData.generateFakeName()
        editProfileScreen
            .checkNameFieldIsDisplayed()
            .typeName(newName)
            .waitSaveButtonWithText()
            .clickSave()
            .clickCancel()
        profileScreen
            .waitUserName()
            .waitEditButton()
            .checkUserNameText(newName)
        oldName = newName
    }

    @Test
    @DisplayName("Редактирование профиля: Имя не сохранится при нажатии кнопки Cancel")
    fun shouldCancelEditingAndKeepOldName() {
        val newName = "ИмяНеСохранится"

        editProfileScreen
            .checkNameFieldIsDisplayed()
            .typeName(newName)
            .clickCancel()
        editProfileScreen.title.assertDoesNotExist()

        profileScreen
            .waitUserName()
            .waitEditButton()
            .checkUserNameText(oldName)
    }
}