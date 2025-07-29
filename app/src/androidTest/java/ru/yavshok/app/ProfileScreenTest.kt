package ru.yavshok.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.fixtures.screens.MainScreenPage
import ru.yavshok.app.fixtures.screens.ProfileScreenPage
import ru.yavshok.app.utils.TestData
import ru.yavshok.app.utils.TestUtils
import io.qameta.allure.kotlin.junit4.DisplayName


@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val profileScreen by lazy { ProfileScreenPage(composeRule) }
    private val mainScreen by lazy { MainScreenPage(composeRule) }

    @Before
    fun setup() {
        TestUtils.disableAnimations()
        val context = composeRule.activity
        TokenStorage(context).logout()

        TestUtils.loginUser(context, TestData.EXISTING_EMAIL, TestData.VALID_PASSWORD)
        TestUtils.launchMainActivityInTestMode()
    }

    @Test
    @DisplayName("Профиль: проверка отображения всех UI элементов")
    fun shouldDisplayAllUIElementsWithCorrectTextsOnProfileScreen() {
        profileScreen
            .waitUserName()
            .checkAvatarIsDisplayed()
            .checkUserNameText(TestData.DISPLAY_NAME)
            .checkAgeStatusText(TestData.AGE_STATUS)
            .checkLogoutButtonIsDisplayed()
            .checkEditButtonIsDisplayed()
            .checkEditButtonTextIsDisplayed()
    }

    @Test
    @DisplayName("Профиль: Проверка отображения статуса: Взрослый котик")
    fun shouldDisplayCorrectAgeStatusForYoungCat() {
        profileScreen.waitUserName()
            .checkAgeStatusText(TestData.AGE_STATUS)

        composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertDoesNotExist()
        profileScreen.checkLogoutButtonIsDisplayed()
    }

    @Test
    @DisplayName("Профиль: Проверка выхода из профиля (переход на главную страницу)")
    fun shouldLogout() {
        profileScreen
            .waitUserName()
            .checkLogoutButtonIsDisplayed()
            .clickLogout()

        mainScreen.waitExistTitle()
    }
}