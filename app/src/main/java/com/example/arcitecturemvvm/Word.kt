package com.example.arcitecturemvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Each @Entity class represents a SQLite table.
 * */
@Entity(tableName = "word_table")
class Word(
    @PrimaryKey
    @ColumnInfo(name = "word")
    var word: String,
    var title: String,
    var urgency: Int
    )