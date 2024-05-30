package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Wish_table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo("Title")
    val title : String = "",
    @ColumnInfo("Desc")
    val description : String = ""
)

