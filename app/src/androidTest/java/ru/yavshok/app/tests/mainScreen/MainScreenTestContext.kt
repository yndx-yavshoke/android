package ru.yavshok.app.tests.mainScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.MainScreenPageObj
import ru.yavshok.app.ui.screens.MainScreen

import ru.yavshok.app.utils.EnvData
import ru.yavshok.app.utils.FakerDataGenerator

import ru.yavshok.app.viewmodel.MainViewModel


@RunWith(AndroidJUnit4::class)
class MainScreenTestContext {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setupPage() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }


    @Test
    fun shouldCheckExistingUser() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(EnvData.email)
            .clickOnCheckEmailButton()
            .assertTextIsVisible("Ты уже в ШОКе")
    }

    @Test
    fun shouldCheckNonExistingUser() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .inputEmail(FakerDataGenerator.randomEmail())
            .clickOnCheckEmailButton()
            .assertTextIsVisible("Ты еще не в ШОКе")
    }

    @Test
    fun shouldLockCheckButtonWhenEmailIsEmpty() {
        MainScreenPageObj(composeRule)
            .waitForScreen()
            .assertEmailFieldIsEmpty()
            .clickOnCheckEmailButton()
            .assertTextIsNotVisible("Ты уже в ШОКе")
            .assertTextIsNotVisible("Ты еще не в ШОКе")
    }

}
