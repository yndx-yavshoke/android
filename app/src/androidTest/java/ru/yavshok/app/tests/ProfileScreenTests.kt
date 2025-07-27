package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.ProfileScreenFixture
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.utility.CredentialsData
import ru.yavshok.app.utility.TestHelper
import ru.yavshok.app.viewmodel.ProfileViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class ProfileScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var profileScreen: ProfileScreenFixture

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        
        // Логинимся для доступа к профилю
        TestHelper.loginAndPrepareUser(
            context,
            CredentialsData.ValidUserData.email,
            CredentialsData.ValidUserData.password
        )
        
        val factory = ViewModelFactory(context)
        val profileViewModel = factory.create(ProfileViewModel::class.java)
        
        composeTestRule.setContent {
            ProfileScreen(
                onEditProfileClick = {},
                onLogout = {},
                viewModel = profileViewModel
            )
        }
        profileScreen = ProfileScreenFixture(composeTestRule)
    }

    @Test
    fun shouldAllElementsOnScreen() {
        profileScreen
            .checkName()
            .checkStatus()
            .checkLogoutButton()
            .checkEditButton()
            .checkPhotoGrid()
            .checkPhoto()
            .checkStatisticGrid()
    }

    @Test
    fun shouldDisplayUserInformation() {
        profileScreen
            .checkName()
            .checkStatus()
    }

    @Test
    fun shouldPhotoElementsWork() {
        profileScreen
            .checkPhoto()
            .checkPhotoGrid()
    }

    @Test
    fun shouldNavigateToEditProfile() {
        profileScreen
            .clickEditButton()
    }

    @Test
    fun shouldLogoutSuccessfully() {
        profileScreen
            .clickLogoutButton()
    }

    @Test
    fun shouldInteractWithAllButtons() {
        profileScreen
            .checkEditButton()
            .checkLogoutButton()
            .clickEditButton()
    }
} 