package ru.yavshok.app.feautures

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.screens.LoginScreenPageObject
import ru.yavshok.app.ui.screens.login.LoginScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.TestHelper.launchMainActivityInTestMode
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.viewmodel.LoginViewModel


@RunWith(AndroidJUnit4::class)
class LoginScreenTestsWithContent {

    @get: Rule
    val composeTestRule  = createComposeRule()

    private lateinit var loginScreen: LoginScreenPageObject

    @Before
    fun setUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val loginViewModel = factory.create(LoginViewModel::class.java)
        composeTestRule.setContent {
            LoginScreen(
                onNavigateToRegister = {},
                onNavigateBack = {},
                onLoginSuccess = {},
                viewModel = loginViewModel
            )
        }
        loginScreen = LoginScreenPageObject(composeTestRule)
    }

    @Test
    fun shouldNotEnterWithWrongPassword() {
        val error = "Неверный email или пароль"
        loginScreen
            .loginShock(Datas.User.email, Datas.FakeUser.password())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotEnterWithWrongEmail() {
        val error = "Неверный email или пароль"
        loginScreen
            .loginShock(Datas.FakeUser.email(), Datas.FakeUser.password())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotEnterWithoutPassword() {
        val error = "Заполните все поля"
        loginScreen
            .loginShock(Datas.FakeUser.email(), "")
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotEnterWithInvalidEmail() {
        val error = "Неверный формат email"
        loginScreen
            .loginShock(" " + Datas.User.email, Datas.User.password)
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun checkAllElementsOnLoginScreen() {
        loginScreen
            .assertTitle()
            .assertEmailField()
            .assertPasswordField()
            .assertLoginButton()
            .assertRegisterButton()
            .assertBackButton()
    }
}

@RunWith(AndroidJUnit4::class)
class LoginScreenTests {

    @get: Rule
    val composeTestRule  = createAndroidComposeRule<MainActivity>()
    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var appPages: AppPages

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        TokenStorage(context).logout()
        appPages = AppPages(composeTestRule)
        scenario = launchMainActivityInTestMode()
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun shouldEnterProfile() {

        appPages.mainScreen
            .waitTitle()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .loginShock(
                Datas.User.email,
                Datas.User.password
            )

        appPages.profileScreen
            .waitCatName()
    }
}