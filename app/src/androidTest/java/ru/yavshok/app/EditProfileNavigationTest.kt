package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class EditProfileNavigationTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private fun openEditProfileScreen() {
        val testUser = TestDataId.registeredUser
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.MainScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.loginButton)
        )
        composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
            .performTextInput(testUser.email)
        composeRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
            .performTextInput(testUser.password)
        composeRule.onNodeWithTag(Tags.LoginScreen.loginButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.EditProfileScreen.screenTitle)
        )
        composeRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle).assertIsDisplayed()
    }

    @Test
    fun elementsOnEditProfileScreenIsVisible() {
        openEditProfileScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.saveChangesButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).assertIsDisplayed()
    }

    @Test
    fun shouldTypeNameOnEditProfileScreen() {
        val testUserName = TestDataGenerator().generateUserData()
        openEditProfileScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle).assertIsDisplayed()
//  Сначала очищаем поле
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField).performTextClearance()
//  Вводим в поле ввода данные
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
            .performTextInput("NewNameKotik")
//  Проверяем что текст ввелся и отображен на экране
        composeRule.onNodeWithTag(Tags.EditProfileScreen.nameTextField)
            .assertTextContains("NewNameKotik")
//  Сохраняем изменения saveChangesButton
        composeRule.onNodeWithTag(Tags.EditProfileScreen.cancelButton).performClick()
//  Ожидание ProfileScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.ProfileScreen.editProfileButton)
        )
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()

    }
}