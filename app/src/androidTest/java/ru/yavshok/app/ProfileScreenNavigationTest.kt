package ru.yavshok.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Test

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class ProfileScreenNavigationTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val testUser = TestDataId.registeredUser

    private fun openProfileScreen() {
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
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
    }

    @Test
    fun elementsOnProfileScreenIsVisible() {
        openProfileScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.ProfileScreen.profileImage).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.nameText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.subtitleText).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
        composeRule.onNodeWithTag(Tags.ProfileScreen.profileImageGrid).assertIsDisplayed()
    }

    @Test
    fun shouldNavigateFromProfileScreenToEditScreen() {
        openProfileScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
//  Кликаем по editProfileButton
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).performClick()
//  Ожидание EditProfileScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.EditProfileScreen.screenTitle))
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.EditProfileScreen.screenTitle).assertIsDisplayed()
//  Проверяем что RegistrationScreen исчезла
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertDoesNotExist()
    }

    @Test
    fun shouldNavigateFromProfileScreenToLoginScreen() {
        openProfileScreen()
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertIsDisplayed()
//  Кликаем по logoutButton
        composeRule.onNodeWithTag(Tags.ProfileScreen.logoutButton).performClick()
//  Ожидание MainScreen
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
//  Элементы отображены на дисплее
        composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
//  Проверяем что ProfileScreen исчезла
        composeRule.onNodeWithTag(Tags.ProfileScreen.editProfileButton).assertDoesNotExist()
    }

}