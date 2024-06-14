package com.grabaride.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.grabaride.ui.theme.SecondaryColor

@Composable
fun CommonOutlineTextField(
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = SecondaryColor,
            unfocusedBorderColor = SecondaryColor,
            unfocusedLabelColor = SecondaryColor
        ),
        value = "",
        label = {
            Text(label)
        },
        onValueChange = {},
        modifier = modifier,
        keyboardOptions = keyboardOptions
    )
}