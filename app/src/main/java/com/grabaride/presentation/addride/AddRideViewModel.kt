package com.grabaride.presentation.addride

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRideViewModel @Inject constructor() : ViewModel() {
    
    private val _rideDataState = mutableStateOf(AddRideState())
    val rideDataState: State<AddRideState> = _rideDataState
    
    
    fun onEvent(event: AddRideEvent) {
        when(event) {
            is AddRideEvent.DateSelected ->{
            
            }
            is AddRideEvent.EnteredCostPerSeat ->{
            
            }
            is AddRideEvent.EnteredFromLocation -> {
            
            }
            is AddRideEvent.EnteredNotes -> {
            
            }
            is AddRideEvent.EnteredRideDuration -> {
            
            }
            is AddRideEvent.EnteredSeatCount -> {
            
            }
            is AddRideEvent.EnteredToLocation -> {
            
            }
            is AddRideEvent.TimeSelected -> {
            
            }
            AddRideEvent.PostRide -> {
            
            }
        }
    }
}