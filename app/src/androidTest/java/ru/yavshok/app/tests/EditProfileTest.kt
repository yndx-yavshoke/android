package ru.yavshok.app.tests
import EditProfilePage
import LoginPage
import MainPage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity

@RunWith(AndroidJUnit4::class)
class EditProfileTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val loginNow = LoginPage(composeTestRule)
    val mainCheck = MainPage(composeTestRule)
    val profileMe = EditProfilePage(composeTestRule)

    private fun openProfileScreen() {
        mainCheck.waitForScreenVisible()
        mainCheck.clickLogin()
        loginNow.typeEmailAndPass("abogsysa@yandex.ru", "12345678m")
        loginNow.clickLogin()
        profileMe.waitForProfileVisible()
    }

    @Test
    fun newOne(){
        openProfileScreen()
        profileMe.editingName("bew bew")
        profileMe.clickSave()
        profileMe.clickCancel()
    }

    @Test
    fun editProfileNameAndCancel() {
        openProfileScreen()
        profileMe.editingName("test cancel")
        profileMe.clickCancel()
    }
}