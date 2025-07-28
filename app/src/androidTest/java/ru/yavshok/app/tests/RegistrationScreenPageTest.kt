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
class RegisterScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainPage = MainPage(composeRule)
    private val loginPage = LoginPage(composeRule)
    private val registrationPage = RegistrationPage(composeRule)

    @Before
    fun setup() {

        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        loginPage.waitForTitle()
        loginPage.clickRegisterButton()
        registrationPage.waitForTitle()
        registrationPage.verifyOnPage()

    }


    @Test
    //Проверка обязательности поля email
    fun testEmptyEmail() {
        //  Тестовые данные

        val UserPassword = FakeDataGenerator.generatePassword()
        val UserAge = FakeDataGenerator.generateAge()
        registrationPage.waitForTitle()
        // Оставляем пустым поле email, остальные поля заполняем данными
        registrationPage.writeEmailPasswordAge("", UserPassword, UserAge)
        // нажимаем Зарегистрироваться, ожидаем текст ошибки
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }




    @Test
  //  Проверка обязательности поля пароль
    fun testEmptyPassword() {
      //  Тестовые данные

        val UserEmail = FakeDataGenerator.generateEmail()
        val UserAge = FakeDataGenerator.generateAge()
        registrationPage.waitForTitle()
      // Оставляем пустым поле password, остальные поля заполняем данными
        registrationPage.writeEmailPasswordAge(UserEmail, "", UserAge)
      // нажимаем Зарегистрироваться, ожидаем текст ошибки
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }


   @Test
   //   Проверка обязательности поля возраст
   fun testEmptyAge() {
       //  Тестовые данные

       val UserEmail = FakeDataGenerator.generateEmail()
       val UserPassword = FakeDataGenerator.generatePassword()
       registrationPage.waitForTitle()
       // Оставляем пустым поле возраст, остальные поля заполняем данными
       registrationPage.writeEmailPasswordAge(UserEmail, UserPassword, "")
       // нажимаем Зарегистрироваться, ожидаем текст ошибки
       registrationPage.clickRegisterButton()
       registrationPage.errorText.assertIsDisplayed()
   }


   @Test
   // Проверка вывода ошибки при попытке написать возраст буквами
   fun testAgeLetter() {
       //  Тестовые данные

       val UserEmail = FakeDataGenerator.generateEmail()
       val UserPassword = FakeDataGenerator.generatePassword()
       val UserAge = Constants.LOGIN_AGE_STRING
       registrationPage.waitForTitle()
       // Оставляем пустым поле возраст, остальные поля заполняем данными
       registrationPage.writeEmailPasswordAge(UserEmail, UserPassword, UserAge)
       // нажимаем Зарегистрироваться, ожидаем текст ошибки
       registrationPage.clickRegisterButton()
       registrationPage.errorText.assertIsDisplayed()
   }
    @OptIn(ExperimentalTestApi::class)
  @Test
  //  Проверка навигации на страницу авторизации при нажатии на кнопку Назад
  fun shouldNavigateFromRegistrationScreenToLoginScreen() {

      registrationPage.waitForTitle()

      // нажимаем Назад
      registrationPage.clickBackButton()
      //Переход на страницу входа

      composeRule.waitUntilAtLeastOneExists(
          timeoutMillis = 5_000L,
          matcher = hasTestTag(Tags.LoginScreen.screenTitle)
      )
  }

}