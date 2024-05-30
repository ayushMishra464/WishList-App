package com.example.wishlistapp

sealed class Route (val route : String) {
     data object HomeScreen : Route("Home_screen")
    data object AddScreen : Route("Add_screen")
}