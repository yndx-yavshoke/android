package ru.yavshok.app.feautures

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.viewmodel.ProfileViewModel
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.repository.ExperimentRepository
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.screens.ProfileScreenPageObject
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.TestHelper
import ru.yavshok.app.utils.TestHelper.launchMainActivityInTestMode

@RunWith(AndroidJUnit4::class)
class ProfileScreenTestsWithSetContent {

    @get: Rule
    val composeTestRule  = createComposeRule()

    private lateinit var profileScreen: ProfileScreenPageObject

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val (tokenStorage, userStore) = TestHelper.loginAndPrepareUser(context, Datas.User.email, Datas.User.password)
        val authRepository = AuthRepository(NetworkModule.provideApiService())
        val experimentRepository = ExperimentRepository(NetworkModule.provideApiService(), context)
        val profileViewModel = ProfileViewModel(
            tokenStorage = tokenStorage,
            authRepository = authRepository,
            experimentRepository = experimentRepository,
            userStore = userStore
        )
        composeTestRule.setContent {
            ProfileScreen(
                viewModel = profileViewModel,
                onEditProfileClick = {},
                onLogout = {},
                isTest = true
            )
        }
        profileScreen = ProfileScreenPageObject(composeTestRule)
    }

    // Тест наличия всех элементов интерфейса на ProfileScreen
    @Test
    fun checkAllElementsOnProfileScreen() {
        profileScreen
            .assertCatAge()
            .assertEditButton()
            .assertLogoutButton()
    }
}

// Тест класс проверки ProfileScreen в контексте всего app с запуском активности
@RunWith(AndroidJUnit4::class)
class ProfileScreenTests {

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

    //Тест проверки функционала выхода их профиля
    @Test
    fun shouldLogout() {
        appPages.mainScreen
            .waitTitle()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .loginShock(Datas.User.email, Datas.User.password)

        appPages.profileScreen
            .waitCatName()
            .clickLogout()

        appPages.mainScreen
            .waitTitle()
            .assertTitle()
    }
}