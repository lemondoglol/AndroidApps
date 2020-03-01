package com.example.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(ItemEntity::class)], version = 1)
abstract class AppDB: RoomDatabase() {

    abstract fun itemsDao(): MyDao

}