package ru.yavshok.app.screens

import androidx.compose.ui.test.junit4.AndroidComposeTestRule

class AppPages (composeTestRule: AndroidComposeTestRule<*, *>) {
    val mainScreen = MainScreenPageObject(composeTestRule)
    val loginScreen = LoginScreenPageObject(composeTestRule)
    val registerScreen = RegisterScreenPageObject(composeTestRule)
    val profileScreen = ProfileScreenPageObject(composeTestRule)
    val editorScreen = EditorScreenPageObject(composeTestRule)
}