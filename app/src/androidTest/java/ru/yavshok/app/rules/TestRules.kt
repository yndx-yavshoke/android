package ru.yavshok.app.rules

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import ru.yavshok.app.MainActivity
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.ui.screens.profile.EditProfileScreen
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory


// Для тестов без навигации
open class ScreenRule {

    @get:Rule
    val composeRule = createComposeRule()

    val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())

    fun setMainScreenContent() {
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
    }

    fun setLoginScreenContent() {
        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }

    fun setRegisterScreenContent() {
        composeRule.setContent {
            RegisterScreen(
                viewModel = viewModel(factory = vmFactory)
            )
        }
    }
}

// Для тестов с навигацией
open class NavigationRule {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
}
