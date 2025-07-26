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
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.data.repository.UserRepository
import ru.yavshok.app.data.storage.TokenStorage
import ru.yavshok.app.screens.AppPages
import ru.yavshok.app.screens.EditorScreenPageObject
import ru.yavshok.app.ui.screens.profile.EditProfileScreen
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.TestHelper
import ru.yavshok.app.utils.TestHelper.launchMainActivityInTestMode
import ru.yavshok.app.viewmodel.EditProfileViewModel

@RunWith(AndroidJUnit4::class)
class EditeScreenTestsWithSetContent {

    @get: Rule
    val composeTestRule  = createComposeRule()

    private lateinit var editScreen: EditorScreenPageObject

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val (tokenStorage, userStore) = TestHelper.loginAndPrepareUser(context, Datas.User.email, Datas.User.password)
        val authRepository = AuthRepository(NetworkModule.provideApiService())
        val userRepository = UserRepository(NetworkModule.provideUserApiService(context))
        val editProfileViewModel = EditProfileViewModel(
            tokenStorage = tokenStorage,
            authRepository = authRepository,
            userRepository = userRepository,
            userStore = userStore
        )
        composeTestRule.setContent {
            EditProfileScreen(
                onNavigateBack = {},
                onProfileUpdated = {},
                viewModel = editProfileViewModel
            )
        }
        editScreen = EditorScreenPageObject(composeTestRule)
    }

    @Test
    fun shouldNotAcceptTooLongName() {
        val error = "Name must be 50 characters or less"
        editScreen
            .changeName(Datas.FakeUser.longName(51))
            .waitErrorMessage(error)
            .assertErrorMessage(error)
    }

    @Test
    fun shouldNotAcceptEmptyName() {
        editScreen
            .enterName("")
            .assertSaveButtonIsNotEnabled()
    }

    @Test
    fun checkAllElementsOnEditScreen() {
        editScreen
            .assertTitle()
            .assertFieldTitle()
            .assertSaveButtonIsEnabled()
            .enterName("")
            .assertNameField()
            .assertCancelButton()
    }
}

@RunWith(AndroidJUnit4::class)
class EditeScreenTests {

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

    @Test
    fun shouldChangeName() {
        val newName = Datas.FakeUser.name()
        appPages.mainScreen
            .waitTitle()
            .clickToLogin()

        appPages.loginScreen
            .waitScreenTitle()
            .loginShock(Datas.User.email, Datas.User.password)

        appPages.profileScreen
            .waitCatName()
            .clickEdit()

        appPages.editorScreen
            .waitTitle()
            .changeName(newName)

        appPages.profileScreen
            .waitCatName()
            .assertCatName(newName)
    }
}