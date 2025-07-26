package ru.yavshok.helpers

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.junit4.ComposeTestRule

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.assertExistsAndDisplayed(tag: String, timeoutMillis: Long = 5000L) {
    this.waitUntilAtLeastOneExists(hasTestTag(tag), timeoutMillis)
    this.onNodeWithTag(tag)
        .assertExists()
        .assertIsDisplayed()
}

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.clickAndAssertExists(tag: String, timeoutMillis: Long = 5000L) {
    this.waitUntilAtLeastOneExists(hasTestTag(tag), timeoutMillis)
    this.onNodeWithTag(tag)
        .assertExists()
        .performClick()
}

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.setInputText(tag: String, value: String, timeoutMillis: Long = 5000L) {
    this.waitUntilAtLeastOneExists(hasTestTag(tag), timeoutMillis)
    this.onNodeWithTag(tag)
        .assertIsDisplayed()
        .performClick()
        .performTextReplacement(value)
}

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.assertTextDisplayed(tag: String, expected: String, timeoutMillis: Long = 5000L) {
    this.waitUntilAtLeastOneExists(hasTestTag(tag), timeoutMillis)
    this.onNodeWithTag(tag)
        .assertIsDisplayed()
        .assertTextContains(expected)
}

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.getTextByTag(tag: String, timeoutMillis: Long = 5000L): String {
    this.waitUntilAtLeastOneExists(hasTestTag(tag), timeoutMillis)
    val node = this.onNodeWithTag(tag)
        .assertExists()
        .assertIsDisplayed()
    val semanticsNode = node.fetchSemanticsNode()
    val textList = semanticsNode.config[SemanticsProperties.Text]
    return textList.firstOrNull()?.text ?: ""
}