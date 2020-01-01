package com.example.arcitecturemvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Each @Entity class represents a SQLite table.
 * */
@Entity(tableName = "word_table")
class Word (

    @ColumnInfo(name = "word")
    var word : String,

    @ColumnInfo(name = "title")
    var title: String
//    var urgency: Int
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}