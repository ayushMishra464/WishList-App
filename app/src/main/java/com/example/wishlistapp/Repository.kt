package com.example.wishlistapp

import androidx.lifecycle.LiveData
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishDao
import kotlinx.coroutines.flow.Flow

class Repository(private val wishDao: WishDao) {

    suspend fun insertItem(wish: Wish) {
        wishDao.insertWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.DeleteWish(wish)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish)
    }

    // flow is taking care of this to execute this asynchronusly , no need to make it suspend function
     fun wishById(id: Long) :  Flow<Wish> {
        return wishDao.queryWish(id)
    }

    fun getAllWish() : Flow<List<Wish>> {
        return wishDao.queryAllWish()

    }
}