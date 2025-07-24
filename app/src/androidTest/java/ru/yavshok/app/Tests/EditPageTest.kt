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
import ru.yavshok.app.Fixtures.EditPage
import ru.yavshok.app.Fixtures.ProfilePage
import ru.yavshok.app.utils_data.login
import ru.yavshok.app.utils_data.waitForTag


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)

class EditProfilePageTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val profilePage = ProfilePage(composeRule)
    val editPage = EditPage(composeRule)


    private val testNames = arrayOf(
        "n0wwcat1",
        "newwcat1",
        "newwcat2",
        "newwcat3",
        "newwcat4"
    )

    private var nameIndex = 0

    private fun getNextTestName(): String {
        val name = testNames[nameIndex]
        nameIndex = (nameIndex + 1) % testNames.size
        return name
    }

    @Before
    fun setUp() {
        login(composeRule)
    }

    @After
    fun logOut() {
        profilePage.clickLogoutButton()
    }

    @Test
    fun typeOfNameOnEditProfileScreen() {
        val testName = getNextTestName()

        profilePage.clickEditProfileButton()

        composeRule.waitForTag(Tags.EditPage.screenTitle)

        editPage.assertScreenTitleIsDisplayed()
            .assertNameTextFieldIsDisplayed()
            .clearNameField()
            .enterName(testName)
            .assertNameContainsText(testName)
            .clickCancelButton()

        composeRule.waitForTag(Tags.ProfilePage.userName)
    }

    @Test
    fun saveEditedNameToProfileScreen() {
        val testName = getNextTestName()

        profilePage.clickEditProfileButton()

        composeRule.waitForTag(Tags.EditPage.screenTitle)

        editPage.assertScreenTitleIsDisplayed()
            .assertNameTextFieldIsDisplayed()
            .clearNameField()
            .enterName(testName)
            .assertNameContainsText(testName)
            .clickSaveButton()
            .clickCancelButton()
        composeRule.waitForTag(Tags.ProfilePage.userName)

        profilePage.assertNameContainsText(testName)
    }
}