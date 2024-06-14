package com.grabaride.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grabaride.domain.Ride

@Composable
fun Rides(modifier: Modifier = Modifier) {
    
    val rides = listOf(
        Ride.default(),
        Ride.default(),
        Ride.default(),
        Ride.default(),
        Ride.default(),
        Ride.default(),
        Ride.default(),
        Ride.default()
    )
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 12.dp)
    ) {
        items(rides) { ride ->
            RideCard(ride)
        }
    }
}