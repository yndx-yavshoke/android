//package ru.yavshok.app.tests

//class bk_files {

//}
// val vmFactory = ViewModelFactory(ApplicationProvider.getApplicationContext())
// @Test
//  fun shouldTypeEmailOnMainScreen() {
//       composeRule.setContent {
//          MainScreen(
//              onNavigateToLogin = {},
//              viewModel = MainViewModel()
//         )
//     }
//    composeRule.onNodeWithTag(Tags.MainScreen.screenTitle).assertIsDisplayed()
//     composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertIsDisplayed()
//     composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).performTextInput("my@email.ru")
//     composeRule.onNodeWithTag(Tags.MainScreen.emailTextField).assertTextContains("my@email.ru")


// }

//  @Test
//  fun shouldTypeEmailOnLoginScreen() {
//      composeRule.setContent {
//         LoginScreen(
//             viewModel = viewModel(factory = vmFactory)
//         )
//     }

//     composeRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
//     composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
//     composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).performTextInput("my@email.ru")
//     composeRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertTextContains("my@email.ru")

//  }