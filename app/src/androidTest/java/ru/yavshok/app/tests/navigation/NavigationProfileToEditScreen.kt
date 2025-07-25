package ru.yavshok.app.tests.navigation

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.ImageLoaderProvider
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.EditPage
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationProfileToEditScreen {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    val mainPage = MainPage(composeRule)
    val loginPage = LoginPage(composeRule)
    val profilePage = ProfilePage(composeRule)
    val editPage = EditPage(composeRule)

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            .edit()
            .clear()
            .commit()

        ImageLoaderProvider.overrideForTest(context)
    }

    @Test
    fun shouldNavigateFromProfileToEditPage() {
        mainPage.waitExists()
        mainPage.goToLogin()

        loginPage.waitExists()
        loginPage.shouldAuthorizeUser()

        profilePage.waitExists()
        composeRule.waitForIdle()

        profilePage.editButton.assertIsDisplayed()
        profilePage.editButton.performClick()

        editPage.waitExists()

        profilePage.header.assertDoesNotExist()
    }
}