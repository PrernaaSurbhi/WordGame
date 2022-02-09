package com.example.wordgame.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
@Entity(tableName = "word_game_table")
data class Word(
    //autoGenerate() ---  Set to true to let SQLite generate the unique id.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word")
    var word:String
    )



