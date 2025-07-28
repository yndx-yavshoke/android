package ru.yavshok.app.tests
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    // Проверка навигации на страницу логина по кнопке в шок из главной страницы
    @Test
    fun shouldNavigateFromMainScreenToLoginScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertDoesNotExist()



    }

    // Проверка навигации на главную страницу по кнопке Назад из страницы логина
    //не стала делать проверки навигации через before,
    // потому что нужно было имитировать поведение пользователя, помещая себя в те же условия
    @Test
    fun shouldNavigateFromLoginScreenToMainScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.backButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()



    }

    // Проверка навигации на страницу регистрации по кнопке Регистрация из страницы логина
    @Test
    fun shouldNavigateFromLoginScreenToRegisterScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertDoesNotExist()



    }

    // Проверка навигации на страницу логина по кнопке Назад из страницы регистрации
    @Test
    fun shouldNavigateFromRegisterScreenToLoginScreen() {
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.LoginScreen.registerButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.RegisterScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.backButton).performClick()

        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))

        composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()



    }

}