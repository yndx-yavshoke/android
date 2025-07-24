package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Tags
import ru.yavshok.app.fixtures.LoginScreen
import ru.yavshok.app.fixtures.MainScreen
import ru.yavshok.app.fixtures.RegisterScreen
import ru.yavshok.app.test_utils.Users
import ru.yavshok.app.test_utils.waitForTag
import ru.yavshok.app.MainActivity

@RunWith(AndroidJUnit4::class)
class RegisterUserFromPropertiesTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mainScreen = MainScreen(composeRule)
    private val loginScreen = LoginScreen(composeRule)
    private val registerScreen = RegisterScreen(composeRule)

    @Test
    fun registerUserFromTestProperties() {
      
        composeRule.waitForTag(Tags.MainScreen.screenTitle)
        mainScreen.assertTitleIsDisplayed()
            .assertLoginButtonIsDisplayed()
            .clickLoginButton()

    
        composeRule.waitForTag(Tags.LoginScreen.screenTitle)
        loginScreen.assertRegisterButtonIsDisplayed()
            .clickRegisterButton()

    
        composeRule.waitForTag(Tags.RegisterScreen.screenTitle)
        registerScreen.assertScreenTitleIsDisplayed()
            .assertEmailFieldIsDisplayed()
            .assertPasswordFieldIsDisplayed()
            .assertAgeFieldIsDisplayed()
            .enterEmailRegisterScreen(Users.registeredUser.email)
            .enterPasswordRegisterScreen(Users.registeredUser.password)
            .enterAgeRegisterScreen(Users.registeredUser.age)
            .assertRegisterButtonIsDisplayed()
            .clickRegisterButton()
        
    }
} 