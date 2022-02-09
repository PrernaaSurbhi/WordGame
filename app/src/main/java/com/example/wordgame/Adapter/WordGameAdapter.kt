package com.example.wordgame.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordgame.R
import com.example.wordgame.db.Word

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
class WordGameAdapter(private var list:List<Word>):RecyclerView.Adapter<WordGameAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val wordItem = list.get(position)
        holder.bind(wordItem.word)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var wordItemView:TextView

        init {
            wordItemView = itemView.findViewById(R.id.textView)
        }

        fun bind(stringText:String){
            wordItemView.text = stringText
        }
    }
}