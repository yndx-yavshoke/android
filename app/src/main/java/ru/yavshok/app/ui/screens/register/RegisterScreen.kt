package ru.yavshok.app.ui.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.yavshok.app.ui.components.TextField
import ru.yavshok.app.ui.components.Button
import ru.yavshok.app.viewmodel.RegisterViewModel
import ru.yavshok.app.viewmodel.ViewModelFactory


@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit = {},
    onRegistrationSuccess: () -> Unit = {},
    viewModel: RegisterViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Reset registration state when screen is disposed (removed from navigation stack)
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetRegistrationState()
        }
    }
    
    // Handle registration success
    LaunchedEffect(uiState.isRegistrationSuccessful) {
        if (uiState.isRegistrationSuccessful) {
            onRegistrationSuccess()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        
        // Title
        Text(
            text = "Регистрация в ШОКе",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Email field
        TextField(
            value = uiState.email,
            onValueChange = { newValue ->
                viewModel.updateEmail(newValue)
            },
            placeholder = {
                Text("Введите Email")
            },
            modifier = Modifier
                .testTag("RegisterEmailField")
                .fillMaxWidth(),
            isError = uiState.errorMessage != null
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Password field
        TextField(
            value = uiState.password,
            onValueChange = { newValue ->
                viewModel.updatePassword(newValue)
            },
            placeholder = {
                Text("Пароль")
            },
            modifier = Modifier
                .testTag("RegisterPasswordField")
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.errorMessage != null
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Age field
        TextField(
            value = uiState.age,
            onValueChange = { newValue ->
                viewModel.updateAge(newValue)
            },
            placeholder = {
                Text("Возраст")
            },
            modifier = Modifier
                .testTag("RegisterAgeField")
                .fillMaxWidth(),
            isError = uiState.errorMessage != null,
            keyboardType = KeyboardType.Number
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Error message
        uiState.errorMessage?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .testTag("errorMessage")
                    .fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Registration button
        Button(
            text = if (uiState.isLoading) "Регистрация..." else "Зарегистрироваться",
            onClick = {
                viewModel.register()
            },
            modifier = Modifier
                .testTag("RegisterSubmitButton")
                .fillMaxWidth(),
            isEnabled = !uiState.isLoading,
            backgroundColor = Color(0xFF007AFF)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Back button
        Button(
            text = "Назад",
            onClick = {
                onNavigateBack()
            },
            modifier = Modifier
                .testTag("RegisterGoBackButton")
                .fillMaxWidth(),
            isEnabled = !uiState.isLoading,
            backgroundColor = Color(0xFF6C757D)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Loading indicator
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("RegisterProgressIndicator")
                    .size(32.dp),
                color = Color(0xFF007AFF)
            )
        }
    }
} 