package com.example.arcitecturemvvm.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arcitecturemvvm.R
import com.example.arcitecturemvvm.data.model.Word

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordTitleView: TextView = itemView.findViewById(R.id.titleView)
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val wordDateView: TextView = itemView.findViewById(R.id.dateView)
//        val urgencyItemView: TextView = itemView.findViewById(R.id.urgencyView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
//        holder.urgencyItemView.text= current.urgency.toString()
        holder.wordTitleView.text = current.title
        holder.wordItemView.text = current.word
        holder.wordDateView.text = " Date created - ${current.date}"
    }

    fun getWordAt(position: Int): Word {
        return words[position]
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}