package ru.yavshok.app.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.fixtures.EditProfileScreenFixture
import ru.yavshok.app.ui.screens.profile.EditProfileScreen
import ru.yavshok.app.utility.CredentialsData
import ru.yavshok.app.utility.TestHelper
import ru.yavshok.app.viewmodel.EditProfileViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory

@RunWith(AndroidJUnit4::class)
class EditProfileScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var editProfileScreen: EditProfileScreenFixture

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        
        // Логинимся для доступа к редактированию профиля
        TestHelper.loginAndPrepareUser(
            context,
            CredentialsData.ValidUserData.email,
            CredentialsData.ValidUserData.password
        )
        
        val factory = ViewModelFactory(context)
        val editProfileViewModel = factory.create(EditProfileViewModel::class.java)
        
        composeTestRule.setContent {
            EditProfileScreen(
                onNavigateBack = {},
                onProfileUpdated = {},
                viewModel = editProfileViewModel
            )
        }
        editProfileScreen = EditProfileScreenFixture(composeTestRule)
    }

    @Test
    fun shouldAllElementsOnScreen() {
        editProfileScreen
            .checkTitle()
            .checkNameTextField()
            .checkSaveButton()
            .checkCancelButton()
    }

    @Test
    fun shouldSaveButtonBeDisabledWhenNameIsEmpty() {
        editProfileScreen
            .clearNameField()
            .checkSaveButtonDisabled()
    }

    @Test
    fun shouldSaveButtonBeEnabledWhenNameIsNotEmpty() {
        editProfileScreen
            .enterName("Test User")
            .checkSaveButtonEnabled()
    }

    @Test
    fun shouldUpdateProfileSuccessfully() {
        editProfileScreen
            .updateProfile("New Name")
    }

    @Test
    fun shouldCancelEditingProfile() {
        editProfileScreen
            .enterName("Some Name")
            .clickCancelButton()
    }

    @Test
    fun shouldValidateNameField() {
        editProfileScreen
            .enterName("Valid Name")
            .checkSaveButtonEnabled()
            .clearNameField()
            .checkSaveButtonDisabled()
    }

    @Test
    fun shouldHandleNameInput() {
        editProfileScreen
            .checkNameTextField()
            .enterName("John Doe")
            .checkSaveButtonEnabled()
    }

    @Test
    fun shouldHandleEmptyNameValidation() {
        editProfileScreen
            .clearNameField()
            .clickSaveButton()
    }
} 