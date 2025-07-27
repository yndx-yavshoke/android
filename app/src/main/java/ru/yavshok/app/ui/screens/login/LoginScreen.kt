package ru.yavshok.app.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.components.Button
import ru.yavshok.app.ui.components.TextField
import ru.yavshok.app.viewmodel.LoginViewModel
import android.nfc.Tag


@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    viewModel: LoginViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Reset login state when screen is disposed (removed from navigation stack)
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetLoginState()
        }
    }
    
    // Handle login success
    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onLoginSuccess()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(Tags.LoginScreen.screenContainer)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        
        // Title
        Text(
            text = "Войти в ШОК",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag(Tags.LoginScreen.screenTitle)
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Email field
        TextField(
            value = uiState.email,
            onValueChange = { newValue ->
                viewModel.updateEmail(newValue)
            },
            placeholder = "Email",
            modifier = Modifier
                .testTag(Tags.LoginScreen.screenEmailTextField)
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
            placeholder = "Пароль",
            modifier = Modifier
                .testTag(Tags.LoginScreen.screenPasswordTextField)
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.errorMessage != null
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
                    .testTag(Tags.LoginScreen.errorMessage)
                    .fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Login and Back buttons row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Login button
            Button(
                text = if (uiState.isLoading) "Вход..." else "В шок",
                            onClick = {
                viewModel.login()
            },
                modifier = Modifier
                    .testTag(Tags.LoginScreen.screenEnterButton)
                    .weight(1f),
                isEnabled = !uiState.isLoading,
                backgroundColor = Color(0xFF007AFF)
            )
            
            // Back button
            Button(
                        text = "Назад",
        onClick = {
            onNavigateBack()
        },
                modifier = Modifier
                    .testTag(Tags.LoginScreen.screenBackButton)
                    .weight(1f),
                isEnabled = !uiState.isLoading,
                backgroundColor = Color(0xFF6C757D)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Registration button
        Button(
            text = "Регистрация",
            onClick = {
                onNavigateToRegister()
            },
            modifier = Modifier.fillMaxWidth(),
            isEnabled = !uiState.isLoading,
            backgroundColor = Color(0xFF007AFF)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Loading indicator
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = Color(0xFF007AFF)
            )
        }
    }
} 