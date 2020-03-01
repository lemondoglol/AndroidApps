package com.example.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ItemEntity {

    @PrimaryKey
    var item_id: Int = 0

    @ColumnInfo(name = "Item Name")
    var item_name: String = ""

    @ColumnInfo(name = "Item Message")
    var item_message: String = ""
}