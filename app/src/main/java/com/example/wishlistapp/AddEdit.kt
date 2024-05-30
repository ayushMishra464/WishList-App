package com.example.wishlistapp

import android.widget.Toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField


import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch

@Composable
fun AddEdit(
    id : Long,
    viewModel: WishViewModel,
    navController: NavController

){
    val context = LocalContext.current

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    if(id != 0L ){
        val wish  = viewModel.getWishbyid(id).collectAsState(
            initial = Wish(0L," "," ")
        )

            viewModel.titleState = wish.value.title
            viewModel.descriptionState = wish.value.description

    }
    else {
        viewModel.titleState =""
        viewModel.descriptionState = ""
    }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppView(title = if (id != 0L) "Update Wish" else "Create Wish" ,
                        onBackClick = {
                            navController.navigateUp() }
                )
            }
        ) { it ->
            Column(modifier = Modifier
                 .padding(it)
                 .wrapContentSize(),
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center

                 ) {

                // to get wishes in list

                 Spacer(modifier = Modifier.height(10.dp))

                 Spacer(modifier = Modifier.height(10.dp))

                     WishTextField(label = "text", value = viewModel.titleState) {
                         viewModel.onTitleChange(it)
                     }

                     Spacer(modifier = Modifier.height(10.dp))

                     WishTextField(label = "Description", value = viewModel.descriptionState) {
                         viewModel.onDescriptionChange(it)
                     }


                 Spacer(modifier = Modifier.height(10.dp))

                 Button(onClick = {

                       if (viewModel.titleState.isNotEmpty()
                           && viewModel.descriptionState.isNotEmpty()){

                         if (id != 0L ) {
                         viewModel.updateWish(
                             Wish(
                                 id = id,
                                 title = viewModel.titleState,
                                 description = viewModel.descriptionState
                             )
                         )

                         snackMessage.value = "Wish has been updated"
                     }

                         else {
                           viewModel.insertWish(
                               Wish(
                                   title = viewModel.titleState.trim(),
                                   description = viewModel.descriptionState.trim()
                               )
                           )

                           snackMessage.value = "Wish has been created"
                       } }

                     scope.launch {
                         navController.navigate(Route.HomeScreen.route)
                         scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                     }

                     }
//                     else {
//
////                        viewModel.insertWish(
////                            Wish(
////                                title = viewModel.titleState.trim(),
////                                description = viewModel.descriptionState.trim()
////                            )
////                        )
////
////                         snackMessage.value = "Wish has been created"

                     //}

                 ) {
                     Text(text = if (id != 0L) "Update Wish" else "Create Wish")
                 }

             }
        }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}





