package ru.yavshok.app.tests.registerScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.RegisterScreenPageObj
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.utils.FakerDataGenerator
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class RegisterScreenTestContext {

    @get:Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    @Before
    fun setupPage() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }


    @Test
    fun shouldThrowWarningForInvalidEmailFormat() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail().substringBefore('@'))
            .inputPassword(FakerDataGenerator.randomPassword())
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()
            .assertTextIsVisible("Неверный формат email")
    }

    @Test
    fun shouldThrowWarningForTooShortPassword() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .inputPassword(FakerDataGenerator.randomShortPassword())
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()
            .assertTextIsVisible("Пароль должен содержать от 5 до 20 символов")
    }

    @Test
    fun shouldThrowWarningForTooLongPassword() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .inputPassword(FakerDataGenerator.randomLongPassword())
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()
            .assertTextIsVisible("Пароль должен содержать от 5 до 20 символов")
    }

    @Test
    fun shouldThrowWarningForEmptyEmail() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .assertEmailFieldIsEmpty()
            .inputPassword(FakerDataGenerator.randomPassword())
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()
            .assertTextIsVisible("Заполните все поля")
    }

    @Test
    fun shouldThrowWarningForEmptyPassword() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .assertPasswordFieldIsEmpty()
            .inputAge(FakerDataGenerator.randomAge())
            .clickOnRegisterButton()
            .assertTextIsVisible("Заполните все поля")
    }

    @Test
    fun shouldThrowWarningForEmptyAge() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .inputPassword(FakerDataGenerator.randomPassword())
            .assertAgeFieldIsEmpty()
            .clickOnRegisterButton()
            .assertTextIsVisible("Заполните все поля")
    }

    @Test
    fun shouldThrowWarningForEmptyFields() {
        RegisterScreenPageObj(composeRule)
            .waitForScreen()
            .assertEmailFieldIsEmpty()
            .assertPasswordFieldIsEmpty()
            .assertAgeFieldIsEmpty()
            .clickOnRegisterButton()
            .assertTextIsVisible("Заполните все поля")
    }

}