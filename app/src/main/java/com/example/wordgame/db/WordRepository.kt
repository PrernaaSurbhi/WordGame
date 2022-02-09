package com.example.wordgame

import androidx.lifecycle.LiveData
import com.example.wordgame.db.Word
import com.example.wordgame.db.WordDAO
import java.util.concurrent.Flow

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
class WordRepository(private val wordDao:WordDAO ) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    fun insert(word:Word){
        wordDao.insertSleepDetail(word)
    }

}