package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.screens.AppPages

/**
 * Тесты главного экрана - адаптация для ТЗ
 * Соответствуют TC-001, TC-002, TC-003, TC-004, TC-005, TC-006
 */
@RunWith(AndroidJUnit4::class)
class MainScreenTests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        // Очищаем авторизацию перед каждым тестом (авторизация в отдельном модуле)
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        TokenStorage(context).logout()

        appPages = AppPages(composeTestRule)
        scenario = launchMainActivityInTestMode()
    }

    /**
     * TC-001: Проверка пользователя, который уже в ШОКе
     */
    @Test
    fun testUserInShok() {
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .assertSuccessMessage()
    }

    /**
     * TC-002: Проверка пользователя, который еще не в ШОКе
     */
    @Test
    fun testUserNotInShok() {
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.FakeUser.email())
            .waitFailMessage()
            .assertFailMessage()
    }

    /**
     * TC-003: Активация кнопки "Я в Шоке" при заполнении поля
     */
    @Test
    fun testButtonActivation() {
        appPages.mainScreen
            .waitTitle()
            .assertCheckButtonIsNotEnabled()
            .enterEmail(Datas.User.email)
            .assertCheckButtonIsEnabled()
    }

    /**
     * TC-004: Повторная проверка ШОКовости
     */
    @Test
    fun testRepeatedShokCheck() {
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .assertSuccessMessage()
            // Повторная проверка
            .clickCheck()
            .waitSuccessMessage()
            .assertSuccessMessage()
    }

    /**
     * TC-005: Проверка ШОКовости другого пользователя
     */
    @Test
    fun testDifferentUsersCheck() {
        // Сначала существующий пользователь
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .assertSuccessMessage()

        // Потом несуществующий (имитируем очистку поля новым вводом)
        appPages.mainScreen
            .enterEmail(Datas.FakeUser.email())
            .clickCheck()
            .waitFailMessage()
            .assertFailMessage()
    }

    /**
     * TC-006: Переход к экрану авторизации через кнопку "В ШОК"
     */
    @Test
    fun testNavigationToLogin() {
        appPages.mainScreen
            .waitTitle()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .assertTitle()
    }

    /**
     * TC-006.2: Переход к авторизации с экрана "Не в ШОКе"
     */
    @Test
    fun testNavigationFromFailureState() {
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.FakeUser.email())
            .waitFailMessage()
            .assertFailMessage()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .assertTitle()
    }

    /**
     * TC-006.3: Переход к авторизации с экрана "В ШОКе"
     */
    @Test
    fun testNavigationFromSuccessState() {
        appPages.mainScreen
            .waitTitle()
            .checkEmail(Datas.User.email)
            .waitSuccessMessage()
            .assertSuccessMessage()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .assertTitle()
    }

    /**
     * Дополнительный тест: Проверка всех элементов экрана
     */
    @Test
    fun testAllScreenElements() {
        appPages.mainScreen
            .waitTitle()
            .assertTitle()
            .assertEmailField()
            .assertCheckButtonIsNotEnabled()
            .assertLoginButton()
    }
}