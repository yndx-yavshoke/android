package ru.yavshok.app.screens.profile

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Rule
import org.junit.Test
import ru.yavshok.app.testdata.TestConfig
import ru.yavshok.app.ui.screens.profile.ProfileScreen
import ru.yavshok.app.viewmodel.ProfileViewModel

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var profileScreenPage: ProfileScreenPage

    @Test
    fun verifyAllElementsDisplayed() {
        launchScreen()
        profileScreenPage.apply {
            verifyAllElementsVisible()
            verifyStatsCorrect()
        }
    }

    @Test
    fun testEditProfileNavigation() {
        var editProfileClicked = false
        launchScreen(onEditProfileClick = { editProfileClicked = true })
        
        profileScreenPage.clickEditProfile()
        assert(editProfileClicked)
    }

    @Test
    fun testLoadingState() {
        launchScreen()
        profileScreenPage.verifyLoadingVisible()
    }

    private fun launchScreen(
        onEditProfileClick: () -> Unit = {},
        onLogout: () -> Unit = {}
    ) {
        composeTestRule.setContent {
            ProfileScreen(
                viewModel = viewModel(),
                onEditProfileClick = onEditProfileClick,
                onLogout = onLogout
            )
        }
        profileScreenPage = ProfileScreenPage(composeTestRule)
    }
}