package com.grabaride.domain

data class Ride(
    val rideId: Int,
    val fromLocation: String,
    val toLocation: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val duration: String,
    val totalSeatCount: Int,
    val bookedSeatCount: Int,
    val costPerPerson: String,
    val note: String,
    val rideOwner: RideOwner,
) {
    
    fun seatsLeft(): String {
        val seatsLeft = totalSeatCount - bookedSeatCount
        return if (seatsLeft > 0)
            "$seatsLeft/$totalSeatCount seats left"
        else
            "Full"
    }
    
    companion object {
        fun default(): Ride {
            return Ride(
                rideId = 1,
                fromLocation = "Gurgaon",
                toLocation = "Chandigarh",
                date = "04 Aug 2024",
                startTime = "05:00",
                endTime = "11:00",
                duration = "06:00",
                totalSeatCount = 4,
                bookedSeatCount = 2,
                costPerPerson = "₹570",
                note = "Sample Note",
                rideOwner = RideOwner(
                    "https://t4.ftcdn.net/jpg/02/29/75/83/240_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg",
                    name = "Rajat",
                    userId = "userId"
                )
            )
        }
    }
}

data class RideOwner(
    val profilePic: String,
    val name: String,
    val userId: String,
)
