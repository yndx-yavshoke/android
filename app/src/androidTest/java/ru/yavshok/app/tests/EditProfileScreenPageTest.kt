package ru.yavshok.app.tests

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage
import ru.yavshok.app.pages.EditProfilePage

@RunWith(AndroidJUnit4::class)
class EditProfileScreenPageTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val profilePage = ProfilePage(composeRule)
    private val editProfilePage = EditProfilePage(composeRule)




    @Before
    fun setup() {

        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        loginPage.waitForTitle()
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
        editProfilePage.waitForTitle()

    }

    @Test
    //Проверка переименования юзера и успешного отображения нового имени
    fun testRenameUser() {
        //  Тестовые данные

        val NewName = FakeDataGenerator.generateName()

        editProfilePage.waitForTitle()
        // пишем новое имя

        editProfilePage.writeNewName(NewName)
        // нажимаем Сохранить
        editProfilePage.clickSaveChangesButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText(NewName).assertIsDisplayed()
    }


    @Test
    //Проверка ввода имени больше 50 символов в поле переименования
    fun testVeryLongName() {
        //  Тестовые данные

        val NewName = FakeDataGenerator.generateVeryLongName()

        editProfilePage.waitForTitle()
        // пишем новое имя

        editProfilePage.writeNewName(NewName)
        // нажимаем Сохранить
        editProfilePage.clickSaveChangesButton()
        editProfilePage.errorText.assertIsDisplayed()
    }

    @Test
    //Проверка ввода пустого значения в поле переименования
    fun testNullName() {
        //  Тестовые данные

        val NewName = ""

        editProfilePage.waitForTitle()
        // пишем новое имя

        editProfilePage.writeNewName(NewName)
        // нажимаем Сохранить
        editProfilePage.clickSaveChangesButton()
        editProfilePage.saveChangesButton.assertIsNotEnabled()
    }

    @Test
    //Проверка что при не нажатии на Save новое имя в поле ввода не сохраняется
    fun testNotSavesNewName() {
        //  Тестовые данные

        val NewName = FakeDataGenerator.generateName()

        editProfilePage.waitForTitle()
        // пишем новое имя

        editProfilePage.writeNewName(NewName)
        // нажимаем Отмена
        editProfilePage.clickCancelButton()
        profilePage.disableAnimations()
        profilePage.waitForNameField()
        composeRule.onNodeWithText(NewName).assertDoesNotExist()
    }

}