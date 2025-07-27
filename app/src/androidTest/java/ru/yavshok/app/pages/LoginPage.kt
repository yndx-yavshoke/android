    package ru.yavshok.app.pages

    import androidx.compose.ui.test.*


    import androidx.compose.ui.test.junit4.ComposeContentTestRule
    import ru.yavshok.app.Tags

    import ru.yavshok.app.ui.screens.login.LoginScreen

    import ru.yavshok.app.viewmodel.LoginViewModel

    class LoginPage(private val composeTestRule: ComposeContentTestRule) {

        // Установка контента экрана логина
        fun setLoginScreenContent(
            onNavigateToRegister: () -> Unit = {},
            onNavigateBack: () -> Unit = {},
            onLoginSuccess: () -> Unit = {},
            viewModel: LoginViewModel // Предполагается, что ViewModel передаётся извне
        ) {
            composeTestRule.setContent {
                LoginScreen(
                    onNavigateToRegister = onNavigateToRegister,
                    onNavigateBack = onNavigateBack,
                    onLoginSuccess = onLoginSuccess,
                    viewModel = viewModel
                )
            }
        }

        fun assertScreenTitleIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.screenTitle).assertIsDisplayed()
        }
        fun assertEmailTextFieldIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField).assertIsDisplayed()
        }
        fun assertPasswordTextFieldIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField).assertIsDisplayed()
        }
        fun assertErrorMessageIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.errorMessage).assertIsDisplayed()
        }
        fun assertLoginButtonIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton).assertIsDisplayed()
        }
        fun assertBackButtonIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.backButton).assertIsDisplayed()
        }
        fun assertGoToRegisterButtonIsDisplayed(){
            composeTestRule.onNodeWithTag(Tags.LoginScreen.goToRegistrScreenButton).assertIsDisplayed()
        }

        fun assertErrorMessageContains(text: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.errorMessage)
                .assertTextContains(text)
        }

        fun assertErrorMessageEquals(expectedText: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.errorMessage)
                .assertTextEquals(expectedText)
        }

        // ВВОД ДАННЫХ
        fun enterEmail(email: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
                .performTextInput(email)
        }

        fun enterPassword(password: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
                .performTextInput(password)
        }

        fun clearEmailField() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
                .performTextClearance()
        }

        fun clearPasswordField() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
                .performTextClearance()
        }

        // КЛИКИ ПО КНОПКАМ
        fun clickLoginButton() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
                .performClick()
        }

        fun clickBackButton() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.backButton)
                .performClick()
        }

        fun clickRegistrationButton() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.goToRegistrScreenButton)
                .performClick()
        }

        // ОЖИДАНИЕ РЕЗУЛЬТАТОВ
        fun waitForLoginError(timeoutMillis: Long = 5000) {
            composeTestRule.waitUntil(timeoutMillis) {
                try {
                    composeTestRule.onNodeWithTag(Tags.LoginScreen.errorMessage)
                        .assertIsDisplayed()
                    true
                } catch (e: AssertionError) {
                    false
                }
            }
        }

        fun waitForErrorMessage(timeoutMillis: Long = 5000) {
            composeTestRule.waitUntil(timeoutMillis) {
                try {
                    composeTestRule.onNodeWithTag(Tags.LoginScreen.errorMessage)
                        .assertIsDisplayed()
                    true
                } catch (e: AssertionError) {
                    false
                }
            }
        }


        // ПРОВЕРКИ СОДЕРЖИМОГО
        fun assertEmailTextFieldContains(email: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.emailTextField)
                .assertTextContains(email)
        }

        fun assertPasswordTextFieldContains(password: String) {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.passwordTextField)
                .assertTextContains(password)
        }

        // СОСТОЯНИЯ КНОПОК
        fun assertLoginButtonIsEnabled() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
                .assertIsEnabled()
        }

        fun assertLoginButtonIsDisabled() {
            composeTestRule.onNodeWithTag(Tags.LoginScreen.loginButton)
                .assertIsNotEnabled()
        }
        fun assertLoadingIndicatorIsDisplayed() {
            composeTestRule.onNodeWithTag("login_loading_indicator") // Или ваш тег
                .assertIsDisplayed()
        }

        fun waitForLoadingIndicator(timeoutMillis: Long = 3000) {
            composeTestRule.waitUntil(timeoutMillis) {
                try {
                    composeTestRule.onNodeWithTag("login_loading_indicator") // Или ваш тег
                        .assertIsDisplayed()
                    true
                } catch (e: AssertionError) {
                    false
                }
            }
        }

        // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ
        fun performLogin(email: String, password: String) {
            enterEmail(email)
            enterPassword(password)
            clickLoginButton()
        }

        fun clearAllFields() {
            clearEmailField()
            clearPasswordField()
        }


    }