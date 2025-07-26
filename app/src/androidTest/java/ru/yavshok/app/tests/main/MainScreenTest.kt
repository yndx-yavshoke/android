package ru.yavshok.app.tests.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ValidUser
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel



@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var mainPage: MainPage

    @Before
    fun setUp() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        mainPage = MainPage(composeRule)
        mainPage.allElementsIsDisplayed()
    }

    @Test
    fun shouldShowErrorMessageWithUnregisteredEmail() {
        val email = Faker().internet().emailAddress()
        with(mainPage) {
            fillEmail(email)
            clickExistButton()
            failureMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowSuccessMessageWithRegisteredEmail() {
        with(mainPage) {
            fillEmail(ValidUser.EMAIL)
            clickExistButton()
            successMessageIsDisplayed()
        }
    }

    @Test
    fun shouldExistsButtonBeDisabledWithEmptyEmail() {
        with(mainPage) {
            existButtonIsDisabled()
        }
    }
}