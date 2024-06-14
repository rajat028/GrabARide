package com.grabaride.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grabaride.ui.theme.SecondaryColor

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    height: Dp = 42.dp,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    text: String,
    singleLine: Boolean = false,
    maxLines: Int = 4,
    hint: String,
    label: String
) {
    
    Column(modifier = modifier) {
        Text(text = label, color = SecondaryColor)
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(height)
                .clip(RoundedCornerShape(10.dp))
                .background(SecondaryColor)
                .padding(horizontal = 10.dp),
            Alignment.CenterStart
        )
        {
            BasicTextField(
                modifier = modifier.height(height),
                value = text,
                onValueChange = onValueChange,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                maxLines = maxLines,
                decorationBox = { innerTextField ->
                    Box {
                        if (text.isEmpty()) {
                            Text(
                                text = hint,
                                color = LocalContentColor.current.copy(alpha = 0.5f)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}