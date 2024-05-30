package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishViewModel (private val repository: Repository = Graph.wishRepository) : ViewModel() {

    var titleState by mutableStateOf("")
    var descriptionState by mutableStateOf("")

    fun onTitleChange(title :String){
        titleState = title
    }

    fun onDescriptionChange(desc :String){
        descriptionState = desc
    }

    lateinit var getAllwish : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllwish = repository.getAllWish()
        }
    }

    fun insertWish(wish: Wish){
        viewModelScope.launch {
            repository.insertItem(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch {
            repository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteWish(wish)
        }
    }

     fun getWishbyid(id: Long): Flow<Wish> {
        return repository.wishById(id)
    }



}