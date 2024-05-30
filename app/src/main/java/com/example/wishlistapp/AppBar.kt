package com.example.wishlistapp

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun AppView(
    title : String,
    onBackClick : () -> Unit
){

    val navigationIcon : (@Composable () -> Unit)? = if (!title.contains("Wishlist")) {
        {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    } else null

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 22.dp)
            )
        },
        elevation = 3.dp,
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFF8370A5),
        navigationIcon = navigationIcon
    )
}
