package com.example.roomwithview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Each @Entity class represents a SQLite table.
@Entity(tableName = "word_table")
data class Word(

    @PrimaryKey @ColumnInfo(name = "word")
    val word: String

)