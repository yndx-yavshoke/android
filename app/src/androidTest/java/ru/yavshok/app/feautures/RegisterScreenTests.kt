package ru.yavshok.app.feautures

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.screens.RegisterScreenPageObject
import ru.yavshok.app.ui.screens.register.RegisterScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.TestHelper.launchMainActivityInTestMode
import ru.yavshok.app.viewmodel.ViewModelFactory
import ru.yavshok.app.viewmodel.RegisterViewModel

@RunWith(AndroidJUnit4::class)
class RegisterScreenTestsWithContent {

    @get: Rule
    val composeTestRule  = createComposeRule()

    private lateinit var registerScreen: RegisterScreenPageObject

    @Before
    fun setUp() {
        val factory = ViewModelFactory(ApplicationProvider.getApplicationContext())
        val registerViewModel = factory.create(RegisterViewModel::class.java)
        composeTestRule.setContent {
            RegisterScreen(
                onNavigateBack = {},
                onRegistrationSuccess = {},
                viewModel = registerViewModel
            )
        }
        registerScreen = RegisterScreenPageObject(composeTestRule)
    }

    @Test
    fun shouldNotRegisterWithoutEmail() {
        val error = "Заполните все поля"
        registerScreen
            .register("", Datas.FakeUser.password(), Datas.FakeUser.age())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotRegisterWithoutPassword() {
        val error = "Заполните все поля"
        registerScreen
            .register(Datas.FakeUser.email(), "", Datas.FakeUser.age())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotRegisterWithoutAge() {
        val error = "Заполните все поля"
        registerScreen
            .register(Datas.FakeUser.email(), Datas.FakeUser.password(), "")
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotRegisterTheSameEmail() {
        val error = "Пользователь с таким email уже существует"
        registerScreen
            .register(Datas.User.email, Datas.User.password, Datas.FakeUser.age())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotRegisterWithInvalidEmail() {
        val error = "Неверный формат email"
        registerScreen
            .register(Datas.FakeUser.password().replace("@", ""), Datas.FakeUser.password(), Datas.FakeUser.age())
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun checkAllElementsOnRegisterScreen() {
        registerScreen
            .assertTitle()
            .assertEmailField()
            .assertPasswordField()
            .assertAgeField()
            .assertRegisterButton()
            .assertBackButton()
    }
}

@RunWith(AndroidJUnit4::class)
class RegisterScreenTests {

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

    @Ignore("Пропускаем этот тест при общем запуске")
    @Test
    fun shouldRegisterNewUser() {
        appPages.mainScreen
            .waitTitle()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .clickRegister()

        appPages.registerScreen
            .waitTitle()
            .register(
                Datas.FakeUser.email(),
                Datas.FakeUser.password(),
                Datas.FakeUser.age()
            )

        appPages.profileScreen.waitCatName()
    }
}