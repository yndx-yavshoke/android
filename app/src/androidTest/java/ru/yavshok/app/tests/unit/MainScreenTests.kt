package ru.yavshok.app.tests.unit

import org.junit.Before
import org.junit.Test
import ru.yavshok.app.base.BaseComposeTest
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.utils.TestConfig

class MainScreenTests : BaseComposeTest() {

    private lateinit var mainPage: MainPage
    private var isContentSet = false

    @Before
    override fun setup() {
        super.setup()
        composeTestRule.mainClock.autoAdvance = true
        mainPage = MainPage(composeTestRule)
    }

    // Единая точка инициализации для всех тестов
    private fun getInitializedPage(): MainPage {
        if (!isContentSet) {
            mainPage.setMainScreenContent()
            composeTestRule.waitForIdle()
            isContentSet = true
        }
        return mainPage
    }

    // Инициализация для тестов авторизации
    private fun getAuthTestPage(): MainPage {
        val page = getInitializedPage()
        page.clearEmailField()
        composeTestRule.waitForIdle()
        return page
    }

    // Инициализация для тестов навигации
    private fun getNavigationTestPage(onNavigateToLogin: () -> Unit): MainPage {
        val page = MainPage(composeTestRule)
        page.setMainScreenContent(onNavigateToLogin = onNavigateToLogin)
        composeTestRule.waitForIdle()
        return page
    }

    // ПРОВЕРКА ОТОБРАЖЕНИЯ ЭЛЕМЕНТОВ НА СТРАНИЦЕ
    @Test
    fun shouldShowTitleOnScreen() {
        getInitializedPage().assertScreenTitleIsDisplayed()
    }

    @Test
    fun shouldShowEmailFieldOnScreen() {
        getInitializedPage().assertEmailTextFieldIsDisplayed()
    }

    @Test
    fun shouldShowEmailFieldPlaceholderOnScreen() {
        getInitializedPage().assertEmailTextFieldPlaceholder()
    }

    @Test
    fun shouldShowCheckButtonOnScreen() {
        getInitializedPage().assertCheckButtonIsDisplayed()
    }

    @Test
    fun shouldShowLoginButtonOnScreen() {
        getInitializedPage().assertLoginButtonIsDisplayed()
    }

    // ТЕСТЫ КНОПОК
    @Test
    fun shouldCheckDisplayAndAccessibilityOfButtons() {
        getInitializedPage().run {
            enterEmail(TestConfig.registeredEmail)
            assertLoginButtonIsDisplayed()
            assertLoginButtonIsEnabled()
            assertCheckButtonIsDisplayed()
            assertCheckButtonIsEnabled()
        }
    }

    // ТЕСТЫ АВТОРИЗАЦИИ
    @Test
    fun shouldCheckRegisteredUser() {
        getAuthTestPage().run {
            enterEmail(TestConfig.registeredEmail)
            clickCheckButton()
            waitForLoginSuccess()
            assertLoginSuccessTextIsDisplayed()
        }
    }

    @Test
    fun shouldCheckUnregisteredUser() {
        getAuthTestPage().run {
            enterEmail(TestConfig.notRegisteredEmail)
            clickCheckButton()
            waitForLoginError()
            assertLoginWrongTextIsDisplayed()
        }
    }

    // ТЕСТЫ ФУНКЦИОНАЛЬНОСТИ КНОПОК
    @Test
    fun shouldTestNavigationButton() {
        var navigationCalled = false

        getNavigationTestPage { navigationCalled = true }
            .clickLoginButton()

        assert(navigationCalled) { "Navigation should be called" }
    }
}