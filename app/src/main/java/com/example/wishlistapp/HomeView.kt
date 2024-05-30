package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add


import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.flow.count
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current

    Scaffold(
        topBar = { AppView(title = "Wishlist", onBackClick = {
            Toast.makeText(context,"Button clicked",Toast.LENGTH_LONG).show()

        })
         },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                containerColor = Color(0xFF8370A5),
                onClick = {
                    navController.navigate(Route.AddScreen.route + "/0L")
                }
            ) {
               Icon(imageVector = Icons.Default.Add, contentDescription = null,
                   tint = Color.White)
            }
        }

    ) {
        val wishList = viewModel.getAllwish.collectAsState(initial = listOf())
        val ii = wishList.value
     LazyColumn(modifier = Modifier
         .fillMaxSize()
         .padding(it)) {
         items(wishList.value, key = {wish -> wish.id}) {
      // find out why are u not able to use items which take list as parameter

       wish ->
             // to delete after swapping
             val dismisstate = rememberDismissState(
                 confirmStateChange = {
                     if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                         viewModel.deleteWish(wish)
                     }
                     true
                 }
             )
             SwipeToDismiss(state = dismisstate,
                 background = {},
                 directions = setOf(DismissDirection.EndToStart,DismissDirection.StartToEnd),
                 dismissThresholds = {FractionalThreshold(0.25f)},
                 dismissContent = {
                     WishItem(wish = wish) {
                         val id = wish.id
                         navController.navigate(Route.AddScreen.route + "/${id}")
                     }
                 }
             )

         }
         }
     }
    }


@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp)
            .clickable { onClick() }
            .shadow(4.dp, shape = CircleShape), // Add shadow to create the protruding effect
        shape = CircleShape, // Set the shape to CircleShape for circular border
        border = null, // Set border to null to remove the border
        backgroundColor = Color.White
    ) {
        Column {
            Text(
                text = wish.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 17.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = wish.description,
                modifier = Modifier.padding(start = 17.dp),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

