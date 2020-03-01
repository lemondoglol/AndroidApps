package com.example.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Data Access Object
 * Define SQL methods here
 * */
@Dao
interface MyDao {

    @Insert
    fun saveItem(item: ItemEntity)

    @Query("select * from ItemEntity")
    fun getItems(): List<ItemEntity>
}