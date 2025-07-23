package ru.yavshok.app.Tests.MainScreenTests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Screens.MainScreenPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mainScreen: MainScreenPage

    @Before
    fun setUp(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }

        mainScreen = MainScreenPage(composeRule)

    }

    @Test
    fun displayedAllViewMainScreen(){

        mainScreen
            .displayedTitle()
            .displayedEmailTextField()
            .displayedCheckButton()
            .displayedCheckButton()
    }

    @Test
    fun displayedPlaceholderInMainPage(){
        mainScreen
            .displayedTitle()
            .displayedEmailTextField()
            .emailTextFieldHasPlaceholder()
    }

    @Test
    fun inputEmailInMainScreen(){

        val email = Faker().internet().emailAddress()

        mainScreen
            .displayedTitle()
            .displayedEmailTextField()
            .verifeInputTextInEmailTextField(email)
    }

    @Test
    fun enabledButtonExist(){

        val emailTrue = Faker().internet().emailAddress()

        mainScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmailInTextField(emailTrue)
            .displayedCheckButton()
            .enableCheckButton()
    }

    @Test
    fun notEnabledButtonExist(){

        val badEmail = Faker().internet().emailAddress().replace("@", "")

        mainScreen
            .displayedTitle()
            .displayedEmailTextField()
            .inputEmailInTextField(badEmail)
            .displayedCheckButton()
            .notEnableCheckButton()
    }




}