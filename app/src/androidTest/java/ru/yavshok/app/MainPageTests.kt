package ru.yavshok.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yavshok.app.modules.MainPage
import ru.yavshok.app.ui.screens.MainScreen
import ru.yavshok.app.viewmodel.MainViewModel
import ru.yavshok.app.dataEmail


@RunWith(AndroidJUnit4::class)
class MainPageTests {

    @get: Rule
    val composeRule = createComposeRule()


    lateinit var page : MainPage
    @Before
    fun setup(){
        composeRule.setContent {
            MainScreen(
                onNavigateToLogin = {},
                viewModel = MainViewModel()
            )
        }
        page = MainPage(composeRule)
    }

    @Test
    fun shouldDisplayNotEnableButtonWhenNotEmailInput(){
        with(page) {
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail("qwerty")
            emailIs("qwerty")
            isVisiblechekShokButton()
            isNotEnableButton()
        }
    }

    @Test
    fun shouldDisplayEnableButtonWhenEmailInput(){
        with(page){
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail("tup@mail.ru")
            emailIs("tup@mail.ru")
            isVisiblechekShokButton()
            isEnableButton()
        }
    }

    @Test
    fun shouldDisplayNotSuccessMessage(){
        with(page){
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail("abacaba@mail.ru")
            isVisiblechekShokButton()
            clickChekShok()
            isVisibleNotInShokText()
        }
    }

    @Test
    fun shouldDisplaySuccessMessage(){
        with(page){
            isVisibleTitle()
            isVisibleEmailInput()
            fillEmail(dataEmail)
            isVisiblechekShokButton()
            clickChekShok()
            isVisibleInShokText()
            isVisibleInShokGif()
        }
    }
}