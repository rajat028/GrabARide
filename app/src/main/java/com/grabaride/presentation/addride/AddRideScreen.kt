package com.grabaride.presentation.addride

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grabaride.components.CommonOutlineTextField
import com.grabaride.GARDatePickerDialog
import com.grabaride.components.TimePickerDialog
import com.grabaride.components.TopBar
import com.grabaride.ui.theme.SecondaryColor
import com.grabaride.utils.convertMillisToDate
import com.grabaride.utils.convertMillisToTime
import java.time.LocalDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRideScreen() {
    
    val currentDateInMillis = remember { mutableStateOf(0L) }
    val calendar = remember { mutableStateOf(Calendar.getInstance()) }
    
    fun isDateSelected(): Boolean {
        return currentDateInMillis.value != 0L
    }
    
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= System.currentTimeMillis()
            }
            
            override fun isSelectableYear(year: Int): Boolean {
                return year >= LocalDate.now().year
            }
        })
    var showDatePicker by remember { mutableStateOf(false) }
    
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.value.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.value.get(Calendar.MINUTE),
        is24Hour = false
    )
    var showTimePicker by remember { mutableStateOf(false) }
    
    if (showDatePicker) {
        GARDatePickerDialog(
            datePickerState = datePickerState,
            currentDateInMillis = currentDateInMillis,
            onVisible = { datePickerVisibility ->
                showDatePicker = datePickerVisibility
            })
    }
    
    if (showTimePicker) {
        TimePickerDialog(onDismissRequest = {
        
        }, confirmButton = {
            TextButton(
                onClick = {
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        set(Calendar.MINUTE, timePickerState.minute)
                    }
                    calendar.value = selectedCalendar
                    showTimePicker = false
                }
            ) { Text("OK") }
        }, dismissButton = {
            TextButton(
                onClick = {
                    showTimePicker = false
                }
            ) { Text("Cancel") }
        }) {
            TimePicker(state = timePickerState)
        }
    }
    
    Scaffold(
        topBar = { TopBar(title = "Add Ride") }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                )
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                
                ChooseDateView(currentDateInMillis, isDateSelected(), onDatePickerClicked = {
                    if (isDateSelected().not())
                        currentDateInMillis.value = System.currentTimeMillis()
                    showDatePicker = true
                })
                
                ChooseTimeView(currentTimeInMillis = calendar) {
                    showTimePicker = true
                }
                
                CommonOutlineTextField(
                    label = "From Location",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {}
                )
                
                CommonOutlineTextField(
                    label = "To Location",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {}
                )
                
                CommonOutlineTextField(
                    label = "Ride Duration(hrs)",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                
                CommonOutlineTextField(
                    label = "Seat Count",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                
                CommonOutlineTextField(
                    label = "Cost Per Seat",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                
                CommonOutlineTextField(
                    label = "Notes For Passengers",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {}
                )
            }
        }
    }
}


@Composable
private fun ChooseDateView(
    currentDateInMillis: MutableState<Long>,
    isDateSelected: Boolean,
    onDatePickerClicked: (Boolean) -> Unit,
) {
    Text(
        text = "Choose Date",
        color = SecondaryColor,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 12.dp)
    )
    
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = SecondaryColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onDatePickerClicked(true) }
    ) {
        Text(
            text = if (isDateSelected.not()) "Select Date" else currentDateInMillis.value.convertMillisToDate(),
            color = if (isDateSelected.not()) Color.White.copy(alpha = 0.5f) else Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(
                    Alignment.CenterStart
                )
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
private fun ChooseTimeView(
    currentTimeInMillis: MutableState<Calendar>,
    onTimePickerClicked: (Boolean) -> Unit,
) {
    Text(
        text = "Start Time",
        color = SecondaryColor,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 12.dp)
    )
    
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = SecondaryColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onTimePickerClicked(true) }
    ) {
        Text(
            text = currentTimeInMillis.value.timeInMillis.convertMillisToTime(),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(
                    Alignment.CenterStart
                )
                .padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun AddRide() {
    AddRideScreen()
}