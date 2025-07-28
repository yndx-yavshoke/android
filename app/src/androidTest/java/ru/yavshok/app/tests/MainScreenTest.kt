package ru.yavshok.app.tests
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.pages.MainPage


@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private val mainPage = MainPage(composeRule)

    @OptIn(ExperimentalTestApi::class)

    @Test
    // Проверка вывода сообщения, что пользователь уже зарегистрирован
    fun testUserExists() {

        // Тестовые данные
        val existingUserEmail = Constants.REGISTER_EMAIL


        mainPage.waitForTitle()
        // Вводим почту существующего пользователя и проверяем на шоковость
        mainPage.checkEmail(existingUserEmail)

        // ожидаем появление текста

        mainPage.waitForExistText()
        // Проверяем, что появился текст о существовании пользователя

        composeRule.onNodeWithText("Ты уже в ШОКе").assertIsDisplayed()
    }

    @Test
    // Проверка вывода сообщения, что пользователь не зарегистрирован
    fun testUserNotExists() {
        // Тестовые данные
        val existingUserEmail = FakeDataGenerator.generateEmail()

        mainPage.waitForTitle()
        // Вводим почту несуществующего пользователя и проверяем на шоковость
        mainPage.checkEmail(existingUserEmail)

        // ожидаем появление текста

        mainPage.waitForNotExistText()
        // Проверяем, что появился текст о том, что пользователь не зарегистрирован

        composeRule.onNodeWithText("Ты еще не в ШОКе").assertIsDisplayed()
    }

    // Проверка навигации на страницу логина по кнопке в шок из главной страницы
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        mainPage.waitForTitle()

    // Нажимаем на кнопку В шок
        mainPage.clickLoginButton()

    //Переход на страницу входа

        composeRule.waitUntilAtLeastOneExists(
                  timeoutMillis = 5_000L,
                  matcher = hasTestTag(Tags.LoginScreen.screenTitle))


    }

}