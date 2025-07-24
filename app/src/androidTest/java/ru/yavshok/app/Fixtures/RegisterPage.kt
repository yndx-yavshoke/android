package ru.yavshok.app.Fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import ru.yavshok.app.Tags

@OptIn(ExperimentalTextApi::class)

class RegisterPage(private val rule: ComposeTestRule) {

    fun assertScreenTitleIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertScreenTitleDoesNotExist(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.emailTxtField).assertIsDisplayed()
        return this
    }

    fun enterEmailRegisterScreen(email: String): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.emailTxtField).performTextInput(email)
        return this
    }


    fun assertPasswordFieldIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.passwordTxtField).assertIsDisplayed()
        return this
    }

    fun enterPasswordRegisterScreen(password: String): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.passwordTxtField).performTextInput(password)
        return this
    }

    fun assertAgeFieldIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.ageTxtField).assertIsDisplayed()
        return this
    }


    fun enterAgeRegisterScreen(age: Int): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.ageTxtField).performTextInput(age.toString())
        return this
    }

    fun assertRegisterButtonIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.registerButton).assertIsDisplayed()
        return this
    }

    fun assertBackButtonIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.backButton).assertIsDisplayed()
        return this
    }

    fun clickRegisterButton(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.registerButton).performClick()
        return this
    }

    fun clickBackButton(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.backButton).performClick()
        return this
    }

    fun assertEmailFieldContains(email:String): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.emailTxtField).assertTextContains(email)
        return this
    }

    fun assertPasswordFieldContains(password: String): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.passwordTxtField).assertTextContains(password)
        return this
    }

    fun assertAgeFieldContains(age: Int): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.ageTxtField).assertTextContains(age.toString())
        return this
    }

    fun assertErrorMessageIsDisplayed(): RegisterPage {
        rule.onNodeWithTag(Tags.RegisterPage.errorMessage).assertIsDisplayed()
        return this
    }

}