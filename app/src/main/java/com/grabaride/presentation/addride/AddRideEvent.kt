package com.grabaride.presentation.addride

sealed class AddRideEvent {
    data class DateSelected(val value: String): AddRideEvent()
    data class TimeSelected(val value: String): AddRideEvent()
    data class EnteredFromLocation(val value: String) : AddRideEvent()
    data class EnteredToLocation(val value: String) : AddRideEvent()
    data class EnteredRideDuration(val value: String) : AddRideEvent()
    data class EnteredSeatCount(val value: String) : AddRideEvent()
    data class EnteredCostPerSeat(val value: String) : AddRideEvent()
    data class EnteredNotes(val value: String): AddRideEvent()
    object PostRide : AddRideEvent()
}