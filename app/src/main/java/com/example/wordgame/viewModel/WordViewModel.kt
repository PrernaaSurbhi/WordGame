package com.example.wordgame.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordgame.WordRepository
import com.example.wordgame.db.Word
import kotlinx.coroutines.launch

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
class WordViewModel(private val wordRepository: WordRepository):ViewModel() {
    val allWords:LiveData<List<Word>> = wordRepository.allWords

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}