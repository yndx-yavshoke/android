package ru.yavshok.app.tests
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.Constants
import ru.yavshok.app.FakeDataGenerator
import ru.yavshok.app.MainActivity
import ru.yavshok.app.Tags
import ru.yavshok.app.pages.MainPage


@RunWith(AndroidJUnit4::class)
class MainTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private val mainPage = MainPage(composeRule)

    @OptIn(ExperimentalTestApi::class)

    @Test
    fun testUserExists() {

        val existingUserEmail = Constants.REGISTER_EMAIL

        mainPage.waitForTitle()
        mainPage.checkEmail(existingUserEmail)
        mainPage.waitForExistText()
        composeRule.onNodeWithText("Ты уже в ШОКе").assertIsDisplayed()
    }

    @Test
    fun testUserNotExists() {
        val existingUserEmail = FakeDataGenerator.generateEmail()

        mainPage.waitForTitle()
        mainPage.checkEmail(existingUserEmail)
        mainPage.waitForNotExistText()
        composeRule.onNodeWithText("Ты еще не в ШОКе").assertIsDisplayed()
    }
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateFromMainScreenToLoginScreen() {
        mainPage.waitForTitle()
        mainPage.clickLoginButton()
        composeRule.waitUntilAtLeastOneExists(
            timeoutMillis = 5_000L,
            matcher = hasTestTag(Tags.LoginScreen.screenTitle))


    }

}