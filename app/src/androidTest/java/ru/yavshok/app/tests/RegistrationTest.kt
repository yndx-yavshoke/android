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

    fun testEmptyEmail() {
        val userPassword = FakeDataGenerator.generatePassword()
        val userAge = FakeDataGenerator.generateAge()

        registrationPage.waitForTitle()
        registrationPage.writeEmailPasswordAge("", userPassword, userAge)
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }




    @Test
    fun testEmptyPassword() {
        val userEmail = FakeDataGenerator.generateEmail()
        val userAge = FakeDataGenerator.generateAge()

        registrationPage.waitForTitle()
        registrationPage.writeEmailPasswordAge(userEmail, "", userAge)
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }

    @Test
    fun testEmptyAge() {
        val userEmail = FakeDataGenerator.generateEmail()
        val userPassword = FakeDataGenerator.generatePassword()

        registrationPage.waitForTitle()
        registrationPage.writeEmailPasswordAge(userEmail, userPassword, "")
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }


    @Test
    fun testAgeLetter() {
        val userEmail = FakeDataGenerator.generateEmail()
        val userPassword = FakeDataGenerator.generatePassword()
        val userAge = Constants.LOGIN_AGE_STRING
        registrationPage.waitForTitle()
        registrationPage.writeEmailPasswordAge(userEmail, userPassword, userAge)
        registrationPage.clickRegisterButton()
        registrationPage.errorText.assertIsDisplayed()
    }
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateFromRegistrationScreenToLoginScreen() {
        registrationPage.waitForTitle()
        registrationPage.clickBackButton()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )
    }

}