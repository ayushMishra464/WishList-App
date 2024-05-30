package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.data.WishDatabase

// this graph here will work like dependency injection
object Graph {

    lateinit var database : WishDatabase

   val wishRepository by lazy {
      Repository(database.wishDao())
  }

    fun provide(context : Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wish.db").build()
    }
}