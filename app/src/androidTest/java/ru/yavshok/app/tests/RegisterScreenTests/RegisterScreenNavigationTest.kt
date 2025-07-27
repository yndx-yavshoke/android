package ru.yavshok.app.tests.RegisterScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.NavigationRule
import ru.yavshok.helpers.assertExistsAndDisplayed
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists
import ru.yavshok.helpers.register

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class RegisterScreenNavigationTest: NavigationRule() {

    private val titleMain = Tags.MainScreen.screenTitle
    private val loginButton = Tags.MainScreen.loginButton

    private val titleLogin = Tags.LoginScreen.screenTitle
    private val registerButtonOnLogin = Tags.LoginScreen.registerButton

    private val titleRegister = Tags.RegisterScreen.screenTitle
    private val backButton = Tags.RegisterScreen.backButton

    private val profileImage = Tags.ProfileScreen.profileImage

    @Before
    fun setup() {
        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")

        composeRule.clickAndAssertExists(loginButton)
        composeRule.assertExistsAndDisplayed(titleLogin)

        composeRule.clickAndAssertExists(registerButtonOnLogin)
        composeRule.assertExistsAndDisplayed(titleRegister)
    }

    @Test
    fun goToLoginScreenFromRegisterScreen() {
        composeRule.clickAndAssertExists(backButton)

        composeRule.onNodeWithTag(backButton).assertDoesNotExist()
        composeRule.assertTextDisplayed(titleLogin, "Войти в ШОК")

    }

    @Ignore("Пропускаем успешную регистрацию, чтобы не засорять бд")
    @Test
    fun registrationOfUnregisteredUser() {
        composeRule.register("fewjkefkwjefjkwefjk@koeffelpwef.fewkopfew", "123456", "18")

        composeRule.onNodeWithTag(loginButton).assertDoesNotExist()
        composeRule.assertExistsAndDisplayed(profileImage)
    }
}