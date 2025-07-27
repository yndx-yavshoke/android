package ru.yavshok.app.Screens

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class PageObjects(private val composeRule: ComposeTestRule) {

    val mainScreen by lazy { MainScreenPage(composeRule) }
    val loginScreen by lazy { LoginScreenPage(composeRule) }
    val registerScreen by lazy { RegisterScreenPage(composeRule) }
    val profileScreen by lazy { ProfileScreenPage(composeRule) }
    val editScreen by lazy { EditProfileScreenPage(composeRule) }

    companion object {
        fun before() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
        }
    }
}

fun pages(composeRule: ComposeTestRule, block: PageObjects.() -> Unit) {
    PageObjects(composeRule).block()
}