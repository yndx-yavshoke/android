package ru.yavshok.app.tests.EditProfileScreenTests

import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Test
import ru.yavshok.app.BuildConfig
import ru.yavshok.app.Tags
import ru.yavshok.app.rules.NavigationRule
import ru.yavshok.helpers.assertExistsAndDisplayed
import ru.yavshok.helpers.assertTextDisplayed
import ru.yavshok.helpers.clickAndAssertExists
import ru.yavshok.helpers.getTextByTag
import ru.yavshok.helpers.login
import ru.yavshok.helpers.setInputText

class EditProfileScreenNavigationTest: NavigationRule() {

    private val profileImage = Tags.ProfileScreen.profileImage
    private val editProfileButton = Tags.ProfileScreen.editProfileButton
    private val profileName = Tags.ProfileScreen.profileName

    private val titleMain = Tags.MainScreen.screenTitle
    private val loginButtonOnMainScreen = Tags.MainScreen.loginButton

    private val titleLogin = Tags.LoginScreen.screenTitle

    private val titleEditProfile = Tags.EditProfileScreen.screenTitle
    private val saveChangesButton = Tags.EditProfileScreen.saveChangesButton
    private val nameField = Tags.EditProfileScreen.nameTextField
    private val cancelButton = Tags.EditProfileScreen.cancelButton

    fun setup() {
        val email = BuildConfig.EMAIL
        val password = BuildConfig.PASSWORD

        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")

        composeRule.clickAndAssertExists(loginButtonOnMainScreen)
        composeRule.assertExistsAndDisplayed(titleLogin)
        composeRule.login(email, password)

        composeRule.assertExistsAndDisplayed(profileImage)

        composeRule.clickAndAssertExists(editProfileButton)
        composeRule.assertTextDisplayed(titleEditProfile, "Edit Profile")
    }

    @Test
    fun shouldTypeNameOnEditProfileScreen() {
        setup()
        val newName = "test"
        composeRule.setInputText(nameField, newName)
        composeRule.assertTextDisplayed(nameField, newName)
    }

    private fun navigateToEditProfile(): String {
        composeRule.assertTextDisplayed(titleMain, "Я в ШОКе")

        composeRule.clickAndAssertExists(loginButtonOnMainScreen)
        composeRule.assertExistsAndDisplayed(titleLogin)
        composeRule.login("example@gmail.com", "Lena89089090")

        composeRule.assertExistsAndDisplayed(profileImage)

        val oldName = composeRule.getTextByTag(profileName)

        composeRule.clickAndAssertExists(editProfileButton)
        composeRule.assertTextDisplayed(titleEditProfile, "Edit Profile")

        return oldName
    }

    @Test
    fun editingUserName() {
        val oldName = navigateToEditProfile()
        val newName = "Neko"

        composeRule.setInputText(nameField, newName)
        composeRule.assertTextDisplayed(nameField, newName)
        composeRule.clickAndAssertExists(saveChangesButton)
        composeRule.clickAndAssertExists(cancelButton)

        val changedName = composeRule.getTextByTag(profileName)
        assert(changedName != oldName)
    }

    @Test
    fun cancelUserNameEditing() {
        val oldName = navigateToEditProfile()
        val newName = "Neko Cancel"

        composeRule.setInputText(nameField, newName)
        composeRule.clickAndAssertExists(cancelButton)

        val changedName = composeRule.getTextByTag(profileName)
        assert(changedName === oldName)
    }

    @Test
    fun changeNameToEmpty() {
        setup()
        val emptyName = ""

        composeRule.setInputText(nameField, emptyName)
        composeRule.assertTextDisplayed(nameField, emptyName)
        composeRule.clickAndAssertExists(saveChangesButton)

        // проверка, что кнопка не кликабельна
        composeRule.onNodeWithTag(saveChangesButton).assertIsNotEnabled()
    }

    @Test
    fun goToProfileScreenFromEditProfileScreen() {
        setup()
        composeRule.clickAndAssertExists(cancelButton)

        composeRule.onNodeWithTag(cancelButton).assertDoesNotExist()
        composeRule.assertExistsAndDisplayed(profileImage)
    }
}