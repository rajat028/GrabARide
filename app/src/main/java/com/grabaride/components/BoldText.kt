package com.grabaride.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BoldText(style: TextStyle, text: String, color: Color = Color.White) {
    Text(
        text = text,
        style = style.copy(fontWeight = FontWeight.Bold),
        color = color,
    )
}