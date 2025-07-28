package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage


@RunWith(AndroidJUnit4::class)
class ProfileScreenPageTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val profilePage = ProfilePage(composeRule)



    @Before
    fun setup() {

        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        loginPage.waitForTitle()
      profilePage.disableAnimations()

    }

//для тестов отображения статуса в зависимости от возраста использовала уже зарегистрированных пользователей,
// потому что регистрация каждый раз при прогоне теста "забивает" каждый раз бд данными,
// пока нет возможности ее очищать и работаем не над индивидуальными проектами - решила реализовать так

    @Test
    //Проверка отображения статуса 'молоденький котик'
    fun testStatusYoungCat() {
        //  Тестовые данные

        val UserEmail = Constants.YOUNG_EMAIL
        val UserPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        // поля заполняем данными
        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем войти
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText("Молоденький котик").assertIsDisplayed()
    }

    @Test
    //Проверка отображения статуса 'Ты взрослый котик'
    fun testStatusAdultCat() {
        //  Тестовые данные

        val UserEmail = Constants.ADULT_EMAIL
        val UserPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        // поля заполняем данными
        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем войти
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText("Взрослый котик").assertIsDisplayed()
    }

    @Test
    //Проверка отображения статуса 'Ты старый котик'
    fun testStatusOldCat() {
        //  Тестовые данные

        val UserEmail = Constants.OLD_EMAIL
        val UserPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        // поля заполняем данными
        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем войти
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText("Старый котик").assertIsDisplayed()
    }

    @Test
    //Проверка выхода из личного кабинета по кнопке Logout
    fun shouldNavigateFromProfileScreenToMainScreen() {
        //  Тестовые данные

        val UserEmail = Constants.REGISTER_EMAIL
        val UserPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        // поля заполняем данными
        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем войти
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        profilePage.clickLogout()
        //здесь мы выходим на главную страницу
        composeRule.onNodeWithText("Я в ШОКе").assertIsDisplayed()
    }


    @Test
    //Проверка перехода на страницу редактирования имени
    fun shouldNavigateFromProfileScreenToRenameScreen() {
        //  Тестовые данные

        val UserEmail = Constants.REGISTER_EMAIL
        val UserPassword = Constants.REGISTER_PASSWORD

        loginPage.waitForTitle()
        // поля заполняем данными
        loginPage.writeEmailPassword(UserEmail, UserPassword)
        // нажимаем войти
        loginPage.clickLoginButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        profilePage.clickRenameButton()
        //здесь мы выходим на страницу редактирования
        composeRule.onNodeWithText("Save Changes").assertIsDisplayed()
    }

}