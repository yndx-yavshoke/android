package ru.yavshok.app.utils_data

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Fixtures.LoginPage
import ru.yavshok.app.Fixtures.MainPage
import ru.yavshok.app.Fixtures.ProfilePage
import ru.yavshok.app.Tags

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.waitForTag(tag: String, timeoutMillis: Long = 15_000L) {
    this.waitUntilAtLeastOneExists(
        matcher = hasTestTag(tag),
        timeoutMillis = timeoutMillis
    )
}

fun login(
    rule: ComposeTestRule,
    email: String = Users.registeredUser.email,
    password: String = Users.registeredUser.password
) {
    val mainPage = MainPage(rule)
    val loginPage = LoginPage(rule)
    val profilePage = ProfilePage(rule)

    rule.waitForTag(Tags.MainScreen.screenTitle)
    mainPage.clickLoginButton()

    rule.waitForTag(Tags.LoginPage.screenTitle)
    loginPage.enterEmail(email)
        .enterPassword(password)
        .clickLoginButton()

    rule.waitForTag(Tags.ProfilePage.userName)

    profilePage.assertUserNameIsDisplayed()
}

fun logout(rule: ComposeTestRule) {
    val profilePage = ProfilePage(rule)
    val mainPage = MainPage(rule)

    rule.waitForTag(Tags.ProfilePage.logoutButton)
    profilePage.clickLogoutButton()

    rule.waitForTag(Tags.MainScreen.screenTitle)
    mainPage.assertTitleIsDisplayed()
}
