package ru.yavshok.app.pages

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule

abstract class BasePage (protected val composeRule: ComposeTestRule) {
    protected open val nodeIsDisplayed: MutableList<String> = mutableListOf()

    @OptIn(ExperimentalTestApi::class)
    fun allElementsIsDisplayed(){
        for (nodeTag in nodeIsDisplayed){
            composeRule.waitUntilAtLeastOneExists(
                hasTestTag(nodeTag),
                6_000L
            )
        }
    }
}