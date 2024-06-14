package com.grabaride

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GARDatePickerDialog(
    datePickerState: DatePickerState,
    currentDateInMillis: MutableState<Long>,
    onVisible: (Boolean) -> Unit,
) {
    DatePickerDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDateInMillis ->
                        if (selectedDateInMillis > currentDateInMillis.value) {
                            currentDateInMillis.value = selectedDateInMillis
                            onVisible(false)
                        }
                    }
                }
            ) { Text("OK") }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onVisible(false)
                }
            ) { Text("Cancel") }
        }) {
        DatePicker(
            state = datePickerState,
        )
    }
}