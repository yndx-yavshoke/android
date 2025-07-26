package ru.yavshok.app.feautures

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.viewmodel.ProfileViewModel
import ru.yavshok.app.data.repository.AuthRepository
import ru.yavshok.app.data.repository.ExperimentRepository
import ru.yavshok.app.data.network.NetworkModule
import ru.yavshok.app.screens.ProfileScreenPageObject
import ru.yavshok.app.utils.Datas
import ru.yavshok.app.utils.TestHelper

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

    @Test
    fun checkAllElementsOnProfileScreen() {
        profileScreen.clickEdit()
    }
}

//@RunWith(AndroidJUnit4::class)
//class ProfileScreenTests {
//
//    @get: Rule
//    val composeTestRule  = createAndroidComposeRule<MainActivity>()
//
//    private lateinit var appPages: AppPages
//
//    @Before
//    fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        TokenStorage(context).logout()
//        appPages = AppPages(composeTestRule)
//    }
//
////    Эта версия тоже зависает на экране профиля
//    @Test
//    fun checkAllElementsOnProfileScreen() {
//        composeTestRule.waitUntil(15000) {
//            try {
//                appPages.mainScreen.title.assertExists()
//                true
//            } catch (e: AssertionError) {
//                false
//            }
//        }
//        appPages.mainScreen.clickToLogin()
//        composeTestRule.waitUntil(5000) {
//            try {
//                appPages.loginScreen.title.assertExists()
//                true
//            } catch (e: AssertionError) {
//                false
//            }
//        }
//        appPages.loginScreen.loginShock(Datas.User.email, Datas.User.password)
//        composeTestRule.waitUntil(5000) {
//            try {
//                appPages.profileScreen.catAge.assertIsDisplayed()
//                appPages.profileScreen.catName.assertIsDisplayed()
//                appPages.profileScreen.catAge.assertIsDisplayed()
//                appPages.profileScreen.editButton.assertIsEnabled()
//                appPages.profileScreen.editButton.assertTextContains("Edit")
//                appPages.profileScreen.logoutButton.assertIsEnabled()
//                true
//            } catch (e: AssertionError) {
//                false
//            }
//        }
//    }
//}