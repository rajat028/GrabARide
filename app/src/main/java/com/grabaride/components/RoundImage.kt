package com.grabaride.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.grabaride.R

@Composable
fun RoundImage(modifier: Modifier = Modifier, size: Dp, url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .border(
            brush = Brush.linearGradient(
                listOf(
                    Color.Green,
                    Color.Red,
                    Color.Blue
                )
            ),
            width = 1.dp,
            shape = CircleShape
        ).clip(CircleShape)
    )
}