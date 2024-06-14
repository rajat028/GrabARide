package com.grabaride.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.grabaride.R
import com.grabaride.domain.Ride
import com.grabaride.domain.RideOwner
import com.grabaride.ui.theme.CardColor
import com.grabaride.ui.theme.SecondaryColor
import com.grabaride.utils.VerticalLine

@Composable
fun RideCard(ride: Ride) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor)
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(Modifier.fillMaxWidth()) {
                TimingsView(ride.startTime, ride.endTime, ride.duration)
                
                FromToGuideline()
                
                OriginDestinationView(ride.fromLocation, ride.toLocation, ride.seatsLeft())
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = ride.costPerPerson,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
            Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 12.dp))
            
            RideOwnerInfoView(ride.rideOwner)
        }
    }
}

@Composable
private fun RideOwnerInfoView(rideOwner: RideOwner) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_car), contentDescription = "Vehicle")
        RoundImage(
            modifier = Modifier.padding(horizontal = 12.dp),
            size = 36.dp,
            url = rideOwner.profilePic
        )
        BoldText(
            text = rideOwner.name,
            style = MaterialTheme.typography.titleSmall,
            color = SecondaryColor
        )
    }
}

@Composable
private fun FromToGuideline() {
    Column(
        Modifier
            .height(100.dp)
            .padding(end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .background(Color.White, CircleShape)
                .size(8.dp)
        
        )
        VerticalLine(color = Color.White, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .background(Color.White, CircleShape)
                .size(8.dp)
        )
    }
}

@Composable
private fun TimingsView(startTime: String, endTime: String, duration: String) {
    Column(
        Modifier
            .height(100.dp)
            .padding(end = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoldText(style = MaterialTheme.typography.bodyMedium, text = startTime)
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = duration,
            color = SecondaryColor
        )
        BoldText(
            style = MaterialTheme.typography.bodyMedium,
            text = endTime
        )
    }
}

@Composable
private fun OriginDestinationView(fromLocation: String, toLocation: String, seatsLeft: String) {
    Column(
        Modifier
            .height(100.dp)
            .padding(end = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BoldText(
            text = fromLocation,
            style = MaterialTheme.typography.bodyMedium
        )
        
        Text(
            text = seatsLeft,
            style = MaterialTheme.typography.bodySmall,
            color = SecondaryColor
        )
        
        BoldText(
            text = toLocation,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}