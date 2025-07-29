package ru.yavshok.app.navigationTests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.pages.LoginScreenPage
import ru.yavshok.app.pages.MainScreenPage
import ru.yavshok.app.pages.ProfileScreenPage
import ru.yavshok.app.pages.RegisterScreenPage
import io.github.serpro69.kfaker.Faker
import org.junit.Ignore
import ru.yavshok.app.Tags

@RunWith(AndroidJUnit4::class)
class RegisterScreenNavigationTest {

    @get: Rule
    val composeRuleActivity = createAndroidComposeRule<MainActivity>()

    private lateinit var registerScreen: RegisterScreenPage
    private lateinit var loginScreen: LoginScreenPage
    private lateinit var mainScreen: MainScreenPage
    private lateinit var profileScreen: ProfileScreenPage
    private val faker = Faker()

    @Before
    fun setup() {
        loginScreen = LoginScreenPage(composeRuleActivity)
        registerScreen = RegisterScreenPage(composeRuleActivity)
        mainScreen = MainScreenPage(composeRuleActivity)
        profileScreen = ProfileScreenPage(composeRuleActivity)
    }

    @Test
    fun navigateFromRegisterToLogin() {
        mainScreen.waitForTitle()
        mainScreen.title.assertIsDisplayed()
        mainScreen.goToLoginButton.performClick()

        loginScreen.waitForTitle()
        loginScreen.title.assertIsDisplayed()
        mainScreen.title.assertDoesNotExist()

        loginScreen.clickGoToRegisterButton()
        registerScreen.title.assertIsDisplayed()
        loginScreen.title.assertDoesNotExist()

        registerScreen.clickBackButton()
        loginScreen.title.assertIsDisplayed()
        registerScreen.title.assertDoesNotExist()
    }

    @Ignore("Пропускаем чтобы не нагружать бэк")
    @Test
    fun successfulRegister() {
        val fakePassword = faker.person.hashCode().toString().take(10)
        val fakeEmail = faker.internet.email()
        val fakeAge = (10..99).random().toString()

        mainScreen.waitForTitle()
        mainScreen.title.assertIsDisplayed()
        mainScreen.goToLoginButton.performClick()

        loginScreen.waitForTitle()
        loginScreen.title.assertIsDisplayed()
        mainScreen.title.assertDoesNotExist()

        loginScreen.clickGoToRegisterButton()
        registerScreen.title.assertIsDisplayed()
        loginScreen.title.assertDoesNotExist()

        registerScreen.fullRegister(fakeEmail, fakePassword, fakeAge)
        @OptIn(androidx.compose.ui.test.ExperimentalTestApi::class)
        composeRuleActivity.waitUntilAtLeastOneExists(
            timeoutMillis = 5000L,
            matcher = hasTestTag(Tags.ProfileScreen.logoutButton)
        )
        profileScreen.logoutButton.assertIsDisplayed()
        registerScreen.title.assertDoesNotExist()
    }
}