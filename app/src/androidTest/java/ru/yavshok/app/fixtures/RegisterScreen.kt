package ru.yavshok.app.fixtures

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.ExperimentalTextApi
import ru.yavshok.app.Tags

@OptIn(ExperimentalTextApi::class)

class RegisterScreen(private val rule: ComposeTestRule) {

    fun assertScreenTitleIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertIsDisplayed()
        return this
    }

    fun assertScreenTitleDoesNotExist(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.screenTitle).assertDoesNotExist()
        return this
    }

    fun assertEmailFieldIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.emailTextField).assertIsDisplayed()
        return this
    }

    fun enterEmailRegisterScreen(email: String): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.emailTextField).performTextInput(email)
        return this
    }


    fun assertPasswordFieldIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertIsDisplayed()
        return this
    }

    fun enterPasswordRegisterScreen(password: String): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).performTextInput(password)
        return this
    }

    fun assertAgeFieldIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertIsDisplayed()
        return this
    }


   fun enterAgeRegisterScreen(age: Int): RegisterScreen {
      rule.onNodeWithTag(Tags.RegisterScreen.ageTextField).performTextInput(age.toString())
      return this
  }

    fun assertRegisterButtonIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.registerButton).assertIsDisplayed()
        return this
    }

    fun assertBackButtonIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.backButton).assertIsDisplayed()
        return this
    }

    fun clickRegisterButton(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.registerButton).performClick()
        return this
    }

    fun clickBackButton(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.backButton).performClick()
        return this
    }

    fun assertEmailFieldContains(email:String): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.emailTextField).assertTextContains(email)
        return this
    }

    fun assertPasswordFieldContains(password: String): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.passwordTextField).assertTextContains(password)
        return this
    }

    fun assertAgeFieldContains(age: Int): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.ageTextField).assertTextContains(age.toString())
        return this
    }

    fun assertErrorMessageIsDisplayed(): RegisterScreen {
        rule.onNodeWithTag(Tags.RegisterScreen.errorMessage).assertIsDisplayed()
        return this
    }




}