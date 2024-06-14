package com.grabaride

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    ADD_RIDE
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Signup : NavigationItem(Screen.SIGNUP.name)
    object AddRide : NavigationItem(Screen.ADD_RIDE.name)
}