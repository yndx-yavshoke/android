package ru.yavshok.app.fixture

import android.content.Context
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ApplicationProvider
import ru.yavshok.app.ImageLoaderProvider
import ru.yavshok.app.pages.EditPage
import ru.yavshok.app.pages.LoginPage
import ru.yavshok.app.pages.MainPage
import ru.yavshok.app.pages.ProfilePage
import ru.yavshok.app.pages.RegisterPage

class YavshokComposeTestFixture(private val composeRule: ComposeTestRule) {

    val mainPage by lazy { MainPage(composeRule) }
    val loginPage by lazy { LoginPage(composeRule) }
    val registerPage by lazy { RegisterPage(composeRule) }
    val profilePage by lazy { ProfilePage(composeRule) }
    val editPage by lazy { EditPage(composeRule) }

    companion object {

        fun before() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()

            ImageLoaderProvider.overrideForTest(context)
        }
    }
}

fun fixture(composeRule: ComposeTestRule, bloc: YavshokComposeTestFixture.() -> Unit) {
    val fixtureInst = YavshokComposeTestFixture(composeRule)
    fixtureInst.bloc()
}
