package com.example.wordgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wordgame.Adapter.WordGameAdapter
import com.example.wordgame.databinding.ActivityMainBinding
import com.example.wordgame.db.Word
import com.example.wordgame.db.WordRoomDataBase
import com.example.wordgame.ui.NewWordActivity
import com.example.wordgame.viewModel.WordViewModel
import com.example.wordgame.viewModel.WordViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var wordGameAdapter:WordGameAdapter ? = null
    lateinit var wordViewModel:WordViewModel
    lateinit var dataBase: WordRoomDataBase
    lateinit var repository: WordRepository
    lateinit var wordViewModelFactory :WordViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        dataBase = Room.databaseBuilder(this@MainActivity,WordRoomDataBase::class.java,"WordRoomDataBase").allowMainThreadQueries().build()
        repository = WordRepository(dataBase.wordDao())
        wordViewModelFactory = WordViewModelFactory(repository)
        wordViewModel = ViewModelProvider(this,wordViewModelFactory).get(WordViewModel::class.java)
        binding.fab.setOnClickListener {
           val intent = Intent(this@MainActivity,NewWordActivity::class.java)
           startActivityForResult(intent,newWordActivityRequestCode)
        }
        wordViewModel.allWords.observe(this,{
            wordGameAdapter = WordGameAdapter(it)
        })
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = wordGameAdapter
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK ){
            intent?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let{
                val word = Word(it)
                 wordViewModel.insert(word)
            }
        }else{
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object{
        const val newWordActivityRequestCode = 1
    }
}