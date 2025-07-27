package ru.yavshok.app.Screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import ru.yavshok.app.Tags
import androidx.compose.ui.test.ExperimentalTestApi

@OptIn(ExperimentalTestApi::class)
class EditProfileScreenPage(private val rule: ComposeTestRule) {

    private val titleTag = Tags.EditScreen.title
    private val nameLineTag = Tags.EditScreen.nameLine
    private val nameFieldTag = Tags.EditScreen.nameTextField
    private val saveButtonTag = Tags.EditScreen.buttonSaveChanges
    private val cancelButtonTag = Tags.EditScreen.buttonCancel
    private val placeholderName = "Name"

    fun waitExists() {
        rule.waitUntilAtLeastOneExists(
            timeoutMillis = 10_000L,
            matcher = hasTestTag(titleTag)
        )
    }

    fun assertNameLineIsDisplayed() = apply {
        rule.onNodeWithTag(nameLineTag).assertIsDisplayed()
    }

    fun assertNameFieldIsDisplayed() = apply {
        rule.onNodeWithTag(nameFieldTag).assertIsDisplayed()
    }

    fun assertSaveButtonIsDisplayed() = apply {
        rule.onNodeWithTag(saveButtonTag).assertIsDisplayed()
    }

    fun assertCancelButtonIsDisplayed() = apply {
        rule.onNodeWithTag(cancelButtonTag).assertIsDisplayed()
    }

    fun clearNameField() = apply {
        rule.onNodeWithTag(nameFieldTag).performTextClearance()
    }

    fun inputName(name: String) = apply {
        rule.onNodeWithTag(nameFieldTag).performTextInput(name)
    }

    fun assertNameInputContains(name: String) = apply {
        rule.onNodeWithTag(nameFieldTag).assertTextContains(name)
    }

    fun assertNamePlaceholderIsDisplayed() = apply {
        rule.onNodeWithTag(nameFieldTag).assertTextContains(placeholderName)
    }

    fun clickSaveButton() = apply {
        rule.onNodeWithTag(saveButtonTag).performClick()
    }

    fun clickCancelButton() = apply {
        rule.onNodeWithTag(cancelButtonTag).performClick()
    }


}





//package ru.yavshok.app.Screens
//
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.ComposeTestRule
//import ru.yavshok.app.Tags
//
//@OptIn(ExperimentalTestApi::class)
//class EditProfileScreenPage(private val rule: ComposeTestRule) {
//
//    private val titleTag = Tags.EditScreen.title
//    private val nameLineTag = Tags.EditScreen.nameLine
//    private val nameFieldTag = Tags.EditScreen.nameTextField
//    private val saveButtonTag = Tags.EditScreen.buttonSaveChanges
//    private val cancelButtonTag = Tags.EditScreen.buttonCancel
//
//    private val placeholderName = "Name"
//
//    fun assertTitleIsDisplayed() = apply {
//        rule.onNodeWithTag(titleTag).assertIsDisplayed()
//    }
//
//    fun assertNameLineIsDisplayed() = apply {
//        rule.onNodeWithTag(nameLineTag).assertIsDisplayed()
//    }
//
//    fun assertNameFieldIsDisplayed() = apply {
//        rule.onNodeWithTag(nameFieldTag).assertIsDisplayed()
//    }
//
//    fun assertSaveButtonIsDisplayed() = apply {
//        rule.onNodeWithTag(saveButtonTag).assertIsDisplayed()
//    }
//
//    fun assertCancelButtonIsDisplayed() = apply {
//        rule.onNodeWithTag(cancelButtonTag).assertIsDisplayed()
//    }
//
//    fun clearNameField() = apply {
//        rule.onNodeWithTag(nameFieldTag).performTextClearance()
//    }
//
//    fun inputName(name: String) = apply {
//        rule.onNodeWithTag(nameFieldTag).performTextInput(name)
//    }
//
//    fun assertNameInputContains(name: String) = apply {
//        rule.onNodeWithTag(nameFieldTag).assertTextContains(name)
//    }
//
//    fun assertNamePlaceholderIsDisplayed() = apply {
//        rule.onNodeWithTag(nameFieldTag).assertTextContains(placeholderName)
//    }
//
//    fun clickSaveButton() = apply {
//        rule.onNodeWithTag(saveButtonTag).performClick()
//    }
//
//    fun clickCancelButton() = apply {
//        rule.onNodeWithTag(cancelButtonTag).performClick()
//    }
//
//    fun timeoutForTitle() = apply {
//        rule.waitUntilExactlyOneExists(
//            timeoutMillis = 5_000L,
//            matcher = hasTestTag(titleTag)
//        )
//    }
//}
