import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
@RunWith(AndroidJUnit4::class)
class RegisterTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val createMe = RegisterPage(composeTestRule)
    val mainCheck = MainPage(composeTestRule)
    val profileMe = EditProfilePage(composeTestRule)
    private fun openRegisterScreen() {
        mainCheck.waitForScreenVisible()
        mainCheck.clickLogin()
        createMe.handleRegisterScreen()
    }

    @Test
    fun successfulRegister(){
        openRegisterScreen()
        createMe.typeEmailAndPassAndAge("hibye@moscow.ru", "1234567bb", "30")
        createMe.clickRegister()
        profileMe.checkProfileVisible()
    }

    @Test
    fun missingEmail(){
        openRegisterScreen()
        createMe.typeEmailAndPassAndAge("", "1234567bb", "30")
        createMe.clickRegister()
        createMe.waitForErrorMessage()
    }

    @Test
    fun missingPass(){
        openRegisterScreen()
        createMe.typeEmailAndPassAndAge("hibye@moscow.ru", "", "30")
        createMe.clickRegister()
        createMe.waitForErrorMessage()
    }

    @Test
    fun missingAge(){
        openRegisterScreen()
        createMe.typeEmailAndPassAndAge("hibye@moscow.ru", "1234567bb", "")
        createMe.clickRegister()
        createMe.waitForErrorMessage()
    }
}