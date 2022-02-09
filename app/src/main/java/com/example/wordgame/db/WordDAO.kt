package com.example.wordgame.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
@Dao
interface WordDAO {
    @Query("SELECT * FROM word_game_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSleepDetail(word: Word)

    @Query("DELETE from word_game_table")
    fun deleteAll()
}