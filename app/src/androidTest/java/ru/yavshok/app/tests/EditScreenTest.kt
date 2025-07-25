package ru.yavshok.app.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.MainActivity
import ru.yavshok.app.data.Data
import ru.yavshok.app.fixture.YavshokComposeTestFixture
import ru.yavshok.app.fixture.fixture

@RunWith(AndroidJUnit4::class)
class EditScreenTest {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = YavshokComposeTestFixture.before()

    @Test
    fun shouldEditName() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.editButton.assertIsDisplayed()
        profilePage.editButton.performClick()

        editPage.waitExists()
        editPage.shouldTypeAndSaveNewName()

        profilePage.waitExists()
        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.NEW_NAME)

        //Back to my name
        profilePage.editButton.performClick()
        editPage.waitExists()
        editPage.nameField.performTextInput("Liza")
        editPage.saveButton.performClick()
    }

    @Test
    fun shouldCanselEditing() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.editButton.assertIsDisplayed()
        profilePage.editButton.performClick()

        editPage.waitExists()
        editPage.shouldCancelEditing()

        profilePage.waitExists()
        profilePage.name.assertIsDisplayed()
        profilePage.name.assertTextContains(Data.MY_NAME)
    }

    @Test
    fun shouldNotAcceptEmptyField() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.editButton.assertIsDisplayed()
        profilePage.editButton.performClick()

        editPage.waitExists()
        editPage.shouldTypeEmptyName()
        editPage.saveButton.assertIsNotEnabled()
    }

    @Test
    fun shouldNotAcceptLongName() = fixture(composeRule) {
        profilePage.navigateToProfilePage()
        composeRule.waitForIdle()

        profilePage.editButton.assertIsDisplayed()
        profilePage.editButton.performClick()

        editPage.waitExists()
        editPage.shouldTypeLongName()
        editPage.saveButton.assertIsDisplayed()
        editPage.saveButton.performClick()

        editPage.errorMessage.assertIsDisplayed()
    }
}
