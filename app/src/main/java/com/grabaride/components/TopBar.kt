package com.grabaride.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.grabaride.ui.theme.CardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                title,
                modifier = Modifier.padding(
                    top = 12.dp
                ),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = CardColor,
            titleContentColor = Color.White
        )
    )
}