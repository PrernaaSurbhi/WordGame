package com.example.wordgame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordgame.WordRepository
import java.lang.IllegalArgumentException

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
class WordViewModelFactory(private val wordRepository: WordRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(WordViewModel::class.java)){
           @Suppress("UNCHECKED_CAST")
           return WordViewModel(wordRepository) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}