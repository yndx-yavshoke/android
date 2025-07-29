package ru.yavshok.app.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.components.TextField
import ru.yavshok.app.ui.components.Button
import ru.yavshok.app.viewmodel.EditProfileViewModel


@Composable
fun EditProfileScreen(
    onNavigateBack: () -> Unit = {},
    onProfileUpdated: () -> Unit = {},
    viewModel: EditProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Load current user name when screen enters
    LaunchedEffect(Unit) {
        viewModel.loadCurrentUserName()
    }
    
    // Reset state when screen is disposed (removed from navigation stack)
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetState()
        }
    }
    
    // Handle profile update success
    LaunchedEffect(uiState.isUpdateSuccessful) {
        if (uiState.isUpdateSuccessful) {
            onProfileUpdated()
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
            text = "Edit Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .testTag(Tags.EditProfileScreen.titleLabel),
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Name label
        Text(
            text = "Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )
        
        // Name field
        TextField(
            value = uiState.name,
            onValueChange = { newValue ->
                viewModel.updateName(newValue)
            },
            placeholder = "Enter your name",
            modifier = Modifier
                .fillMaxWidth()
                .testTag(Tags.EditProfileScreen.nameField),
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
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Save Changes button
        Button(
            text = if (uiState.isLoading) "Saving..." else "Save Changes",
            onClick = {
                viewModel.updateProfile()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(Tags.EditProfileScreen.saveButton),
            isEnabled = !uiState.isLoading && uiState.name.isNotBlank(),
            backgroundColor = Color(0xFF007AFF)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Cancel button
        Button(
            text = "Cancel",
            onClick = {
                onNavigateBack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(Tags.EditProfileScreen.cancelButton),
            isEnabled = !uiState.isLoading,
            backgroundColor = Color(0xFF6C757D)
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