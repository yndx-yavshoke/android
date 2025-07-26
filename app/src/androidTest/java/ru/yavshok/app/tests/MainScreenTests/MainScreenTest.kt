package ru.yavshok.app.tests.MainScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.ScreenRule
import ru.yavshok.helpers.assertExistsAndDisplayed
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists
import ru.yavshok.helpers.setInputText

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest: ScreenRule() {

    private val title = Tags.MainScreen.screenTitle
    private val emailField = Tags.MainScreen.emailTextField
    private val checkEmailButton = Tags.MainScreen.checkEmailButton
    private val existEmailText = Tags.MainScreen.existEmailText
    private val existEmailGif = Tags.MainScreen.existEmailGif
    private val notExistEmailText = Tags.MainScreen.notExistEmailText

    @Before
    fun setup() {
        setMainScreenContent()
        composeRule.assertTextDisplayed(title, "Я в ШОКе")
    }

    @Test
    fun shouldTypeEmailOnMainScreen() {
        val email = "test@test.com"
        composeRule.setInputText(emailField, email)
        composeRule.assertTextDisplayed(emailField, email)
    }

    @Test
    fun checkingExistEmail() {
        val email = BuildConfig.EMAIL

        composeRule.setInputText(emailField, email)
        composeRule.assertTextDisplayed(emailField, email)

        composeRule.clickAndAssertExists(checkEmailButton)

        composeRule.assertTextDisplayed(existEmailText, "Ты уже в ШОКе")
        composeRule.assertExistsAndDisplayed(existEmailGif)
    }

    @Test
    fun checkingNotExistEmail() {
        composeRule.setInputText(emailField, "invalid@invalid.com")
        composeRule.assertTextDisplayed(emailField, "invalid@invalid.com")

        composeRule.clickAndAssertExists(checkEmailButton)

        composeRule.assertTextDisplayed(notExistEmailText, "Ты еще не в ШОКе")
    }

}