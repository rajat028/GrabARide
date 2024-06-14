package com.grabaride.presentation.addride

data class AddRideState(
    val rideDate: String ="",
    val startTime: String = "",
    val fromLocation: String = "",
    val toLocation: String = "",
    val rideDuration: Int = 0,
    val seatCount: Int = 0,
    val costPerSeat: Int = 0,
    val notes: String = "",
)
