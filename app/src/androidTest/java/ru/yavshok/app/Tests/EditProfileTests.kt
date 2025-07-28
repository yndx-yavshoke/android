package ru.yavshok.app.Tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import androidx.compose.ui.test.ExperimentalTestApi
import org.junit.Before
import org.junit.After
import ru.yavshok.app.Tags
import ru.yavshok.app.test_utils.loginThroughUI
import ru.yavshok.app.test_utils.waitForTag


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)

class EditProfileScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val profileScreen = ProfileScreen(composeRule)
    val editProfileScreen = EditProfileScreen(composeRule)


    private val testNames = arrayOf(
        "New Cat",
        "Новый котик",
        "Nouveau Chat",
        "Neue Katze",
        "Gato Nuevo"
    )

    private var currentNameIndex = 0

    private fun getNextTestName(): String {
        val name = testNames[currentNameIndex]
        currentNameIndex = (currentNameIndex + 1) % testNames.size
        return name
    }

    @Before
    fun setUp() {
        loginThroughUI(composeRule)
    }

    @After
    fun logOut() {
        profileScreen.clickLogoutButton()
    }

    @Test
    fun shouldTypeNameOnEditProfileScreen() {
        val testName = getNextTestName()

        profileScreen.clickEditProfileButton()

        composeRule.waitForTag(Tags.EditProfileScreen.screenTitle)

        editProfileScreen.assertScreenTitleIsDisplayed()
            .assertNameTextFieldIsDisplayed()
            .clearNameField()
            .enterName(testName)
            .assertNameContainsText(testName)
            .clickCancelButton()

        composeRule.waitForTag(Tags.ProfileScreen.userName)





    }

    @Test
    fun shouldSaveEditedNameToProfileScreen() {
        val testName = getNextTestName()

        profileScreen.clickEditProfileButton()

        composeRule.waitForTag(Tags.EditProfileScreen.screenTitle)

        editProfileScreen.assertScreenTitleIsDisplayed()
            .assertNameTextFieldIsDisplayed()
            .clearNameField()
            .enterName(testName)
            .assertNameContainsText(testName)
            .clickSaveButton()
            .clickCancelButton()
        composeRule.waitForTag(Tags.ProfileScreen.userName)

        profileScreen.assertNameContainsText(testName)
    }
}