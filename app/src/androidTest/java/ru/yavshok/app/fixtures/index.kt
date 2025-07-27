package ru.yavshok.app.fixtures

import androidx.compose.ui.test.junit4.AndroidComposeTestRule

class index (composeTestRule: AndroidComposeTestRule<*, *>) {
    val mainScreen = MainScreenFixture(composeTestRule)
    val loginScreen = LoginScreenFixture(composeTestRule)
    val profileScreen = ProfileScreenFixture(composeTestRule)
}