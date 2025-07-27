package ru.yavshok.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import ru.yavshok.app.R
import ru.yavshok.app.Tags
import ru.yavshok.app.ui.components.CustomButton
import ru.yavshok.app.ui.components.TextField
import ru.yavshok.app.viewmodel.MainViewModel
import java.util.concurrent.TimeUnit


@Composable
fun MainScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: MainViewModel
) {
    val email by viewModel.email
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage
    val isEmailExists by viewModel.isEmailExists
    val showConfetti by viewModel.showConfetti

    val context = LocalContext.current
    
    // Create confetti party configuration
    val party = remember {
        Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.0),
            emitter = Emitter(duration = 3, TimeUnit.SECONDS).max(100)
        )
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag(Tags.MainScreen.screenContainer)
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Я в ШОКе",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .testTag(Tags.MainScreen.screenTitle)
            )
            
            // Email input
            TextField(
                value = email,
                onValueChange = { newValue ->
                    viewModel.onEmailChange(newValue)
                },
                placeholder = "Введите Email",
                modifier = Modifier
                    .testTag(Tags.MainScreen.emailTextField)
                    .padding(bottom = 16.dp),
                isError = errorMessage != null && !isEmailExists
            )
            
            // Success message and GIF when email exists
            if (isEmailExists) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.drawable.happy_cat)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Happy Cat",
                    modifier = Modifier
                        .testTag(Tags.MainScreen.imageCheckEmailTru)
                        .fillMaxWidth(0.9f)
                        .height(300.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Ты уже в ШОКе",
                    color = Color(0xFF4CAF50), // Green color
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .testTag(Tags.MainScreen.lineCheckEmailTrue)
                        .padding(bottom = 16.dp)
                )
                
            }
            
            if (!isEmailExists) {
                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .testTag(Tags.MainScreen.lineCheckEmailFalse)
                            .padding(bottom = 16.dp)
                    )
                }
            }
            
            // "Я в шоке?" button
            CustomButton(
                text = if (isLoading) "Проверяем..." else "Я в шоке?",
                onClick = { 
                    viewModel.checkEmailExists() 
                },
                isEnabled = viewModel.isEmailValid() && !isLoading,
                backgroundColor = Color(0xFF007AFF),
                disabledBackgroundColor = Color.Gray,
                modifier = Modifier
                    .testTag(Tags.MainScreen.checkLoginButton)
                    .padding(bottom = 12.dp)
            )
            
            // "В шок" button
            CustomButton(
                text = "В шок",
                onClick = {
                    onNavigateToLogin()
                },
                backgroundColor = Color(0xFF007AFF),
                modifier = Modifier.testTag(Tags.MainScreen.loginButton)
            )
            
            Spacer(modifier = Modifier.height(200.dp))
        }
        
        // Confetti animation overlay
        if (showConfetti) {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(party),
                updateListener = object : OnParticleSystemUpdateListener {
                    override fun onParticleSystemEnded(system: nl.dionsegijn.konfetti.core.PartySystem, activeSystems: Int) {
                        if (activeSystems == 0) {
                            viewModel.hideConfetti()
                        }
                    }
                }
            )
        }
    }
}