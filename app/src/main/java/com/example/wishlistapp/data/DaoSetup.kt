package com.example.wishlistapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishlistapp.data.Wish
import java.util.concurrent.Flow


@Dao
abstract class WishDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertWish(wish: Wish)

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Delete

    abstract suspend fun DeleteWish(wish: Wish)

    @Query("Select * from Wish_table where id =:id")
    abstract fun queryWish(id :Long) : kotlinx.coroutines.flow.Flow<Wish>
    // you can use livedata or flow both fulfill the purpose.

    @Query("Select * from Wish_table ")
    abstract fun queryAllWish(): kotlinx.coroutines.flow.Flow<List<Wish>>

}