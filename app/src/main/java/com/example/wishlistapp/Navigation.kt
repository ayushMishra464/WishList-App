package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel :WishViewModel = WishViewModel(), navController: NavHostController = rememberNavController()){
       NavHost(navController = navController, startDestination = Route.HomeScreen.route ) {

        composable(Route.HomeScreen.route){
            HomeView(navController,viewModel,)
        }

           composable(Route.AddScreen.route + "/{id}",
               arguments = listOf(navArgument("id"){
                   type = NavType.LongType
                   defaultValue = 0L
                   nullable = false
               })
           ){it ->
               val id = if (it.arguments != null) it.arguments!!.getLong("id") else 0L
               AddEdit(id,viewModel,navController)
           }

       }
}
