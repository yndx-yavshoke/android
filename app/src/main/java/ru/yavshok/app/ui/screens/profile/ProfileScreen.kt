package ru.yavshok.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import ru.yavshok.app.ui.icons.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import ru.yavshok.app.R
import ru.yavshok.app.viewmodel.ProfileUiState
import ru.yavshok.app.viewmodel.ProfileViewModel
import androidx.compose.ui.platform.testTag
import ru.yavshok.app.Tags


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onEditProfileClick: () -> Unit,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    // Reactive refresh - update profile when screen is displayed
    LaunchedEffect(Unit) {
        viewModel.refreshProfile()
    }
    
    // Create ImageLoader for GIF support
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            uiState.profile?.let { profile ->
                // Profile header
                ProfileHeader(
                    profile = profile,
                    imageLoader = imageLoader,
                    onEditProfileClick = onEditProfileClick,
                    onLogoutClick = {
                        viewModel.logout()
                        onLogout()
                    }
                )
                
                // Photo grid
                PhotoGrid(photos = profile.photos)
            }
        }
        
        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ProfileHeader(
    profile: ru.yavshok.app.data.model.Profile,
    imageLoader: ImageLoader,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Top row with profile info and share button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Profile image and info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.profile)
                        .build(),
                    contentDescription = "Profile Image",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .testTag(Tags.ProfileScreen.userAvatar),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.width(20.dp))
                
                // Name and subtitle
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = profile.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .testTag(Tags.ProfileScreen.userName)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = profile.subtitle,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .testTag(Tags.ProfileScreen.userStatus)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
                 Row(
             modifier = Modifier.fillMaxWidth(),
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.SpaceBetween
         ) {
             Row(
                 horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
             ) {
                 StatItem(count = profile.postsCount, label = "Постов")
                 StatItem(count = profile.followersCount, label = "Подписчиков")
                 StatItem(count = profile.likesCount, label = "Лайков")
             }
             IconButton(
                 onClick = { 
                     onLogoutClick() 
                 },
                 modifier = Modifier.size(24.dp)
                     .testTag(Tags.ProfileScreen.buttonLogOut)
             ) {
                 Icon(
                     Logout,
                     contentDescription = "Logout",
                     tint = Color.Black,
                 )
             }
         }

        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Edit Profile button
        Button(
            onClick = {
                onEditProfileClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .testTag(Tags.ProfileScreen.buttonEdit),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Edit Profile",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Hidden logout functionality (keeping for navigation but not visible)
        // You can trigger logout via other means if needed
    }
}

@Composable
private fun StatItem(count: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
private fun PhotoGrid(photos: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.height(400.dp) // Fixed height for the grid
    ) {
        items(photos) { photo ->
            val drawableRes = when (photo) {
                "1" -> R.drawable.photo_1
                "2" -> R.drawable.photo_2
                "3" -> R.drawable.photo_3
                "4" -> R.drawable.photo_4
                else -> R.drawable.photo_1
            }
            
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = "Photo $photo",
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        // TODO: Open photo detail
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
} 