package ru.yavshok.app.test_utils

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import ru.yavshok.app.fixtures.LoginScreen
import ru.yavshok.app.fixtures.MainScreen
import ru.yavshok.app.fixtures.ProfileScreen


@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.waitForTag(tag: String, timeoutMillis: Long = 10_000L) {
    this.waitUntilAtLeastOneExists(
        matcher = hasTestTag(tag),
        timeoutMillis = timeoutMillis
        )
}

fun loginThroughUI(
    rule: ComposeTestRule,
    email: String = Users.registeredUser.email,
    password: String = Users.registeredUser.password
) {
    val mainScreen = MainScreen(rule)
    val loginScreen = LoginScreen(rule)
    val profileScreen = ProfileScreen(rule)

    rule.waitForTag(Tags.MainScreen.screenTitle)
    mainScreen.clickLoginButton()

    rule.waitForTag(Tags.LoginScreen.screenTitle)
    loginScreen.enterEmail(email)
        .enterPassword(password)
        .clickLoginButton()

    rule.waitForTag(Tags.ProfileScreen.userName)

    profileScreen.assertUserNameIsDisplayed()
}

fun logoutThroughUI(rule: ComposeTestRule) {
    val profileScreen = ProfileScreen(rule)
    val mainScreen = MainScreen(rule)

    rule.waitForTag(Tags.ProfileScreen.logoutButton)
    profileScreen.clickLogoutButton()

    rule.waitForTag(Tags.MainScreen.screenTitle)
    mainScreen.assertTitleIsDisplayed()
}

