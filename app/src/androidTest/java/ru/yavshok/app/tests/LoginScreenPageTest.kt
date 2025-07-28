package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.RegistrationPage

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    //val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val registrationPage = RegistrationPage(composeRule)

    @Before
    fun setup() {
        mainPage.waitForTitle()
        mainPage.clickLoginButton()

    }


    @Test
    //Проверка обязательности поля email
    fun testEmptyEmail() {
        //  Тестовые данные

        val UserPassword = Constants.REGISTER_PASSWORD
        loginPage.waitForTitle()
        // Оставляем пустым поле email, остальные поля заполняем данными
        loginPage.writeEmailPassword("", UserPassword)
        // нажимаем Войти, ожидаем текст ошибки
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

   @Test
   //Проверка обязательности поля пароль
   fun testEmptyPassword() {
       //  Тестовые данные

       val UserEmail = Constants.REGISTER_EMAIL
       loginPage.waitForTitle()
       // Оставляем пустым поле пароль, остальные поля заполняем данными
       loginPage.writeEmailPassword(UserEmail, "")
       // нажимаем Войти, ожидаем текст ошибки
       loginPage.clickLoginButton()
       loginPage.errortext.assertIsDisplayed()
   }

    @Test
    //Проверка ввода неверного пароля
    fun testWrongPassword() {
        //  Тестовые данные

        val UserEmail = Constants.REGISTER_EMAIL
        val UserPassword = FakeDataGenerator.generatePassword()
        loginPage.waitForTitle()

        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем Войти, ожидаем текст ошибки
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @Test
    //Проверка ввода незарегистрированного пользователя
    fun testNotRegistUser() {
        //  Тестовые данные

        val UserEmail = FakeDataGenerator.generateEmail()
        val UserPassword = FakeDataGenerator.generatePassword()
        loginPage.waitForTitle()

        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем Войти, ожидаем текст ошибки
        loginPage.clickLoginButton()
        loginPage.errortext.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    //Навигация на страницу регистрации
    fun shouldNavigateFromLoginScreenToRegistrationScreen() {

        loginPage.waitForTitle()

        // нажимаем Регистрация
        loginPage.clickRegisterButton()
        //Переход на страницу регистрации

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle)
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    //Навигация на главную страницу (страницу проверки шоковости)
    fun shouldNavigateFromLoginScreenToMainScreen() {

        loginPage.waitForTitle()

        // нажимаем Назад
        loginPage.clickBackButton()
        //Переход на страницу проверки шоковости

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
    }



}