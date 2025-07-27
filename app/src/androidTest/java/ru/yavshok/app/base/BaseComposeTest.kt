package ru.yavshok.app.base

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity

@RunWith(AndroidJUnit4::class)
abstract open class BaseComposeTest {

    // Используем createAndroidComposeRule() для получе ния AndroidComposeTestRule
    @get:Rule
    open val composeTestRule = createComposeRule()

    @Before
    open fun setup() {
        composeTestRule.mainClock.autoAdvance = false
    }
}