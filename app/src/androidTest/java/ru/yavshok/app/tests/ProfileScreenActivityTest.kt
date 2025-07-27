package ru.yavshok.app.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.pages.ProfileScreenPage
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalTextApi::class)
@RunWith(AndroidJUnit4::class)
class ProfileScreenActivityTest {

    @get:Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private val testUser = User("abra@mail.ru", "cadabra", 30)
    private var profileScreen = ProfileScreenPage(composeRuleActivity)
    private val loginScreen = LoginScreenPage(composeRuleActivity)

    @OptIn(ExperimentalTestApi::class)
    @Before
    fun setUp() {
        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.MainScreen.screenTitle)
        )
        composeRuleActivity.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
        composeRuleActivity.onNodeWithTag(Tags.MainScreen.loginButton).assertIsDisplayed()
        composeRuleActivity.onNodeWithTag(Tags.MainScreen.loginButton).performClick()

        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle)
        )

        loginScreen
            .typeEmail(testUser.username)
            .typePassword(testUser.password)
            .clickLoginButton()

        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 9_000L,
            matcher = hasTestTag(Tags.ProfileScreen.exitButton)
        )
    }

    @Test
    fun shouldDisplayAllElementsOnProfileScreen() {
        profileScreen
            .displayProfileTextName()
            .displayProfileImage()
            .displayEditProfileButton()
            .displayProfileTextStatus()
            .displayExitButton()
            .clickExitButton()
    }

}