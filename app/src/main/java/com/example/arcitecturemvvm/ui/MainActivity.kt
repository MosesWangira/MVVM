package com.example.arcitecturemvvm.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arcitecturemvvm.R
import com.example.arcitecturemvvm.util.toast
import com.example.arcitecturemvvm.data.adapters.WordListAdapter
import com.example.arcitecturemvvm.viewModel.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity()  {

    lateinit var fab_icon : ImageView
    private lateinit var wordViewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab_icon = findViewById(R.id.fab)
        Picasso.with(this).load(R.drawable.add_note).into(fab_icon)

        fab_icon.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)


        val adapter = WordListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * When Activity first starts, the ViewModelProviders will create the ViewModel.
         * When the activity is destroyed, for example through a configuration change, the ViewModel persists.
         * When the activity is re-created, the ViewModelProviders return the existing ViewModel.
         * */
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                wordViewModel.delete(adapter.getWordAt(viewHolder.adapterPosition))
                //testing suspend functions
                toast("note deleted")
            }
        }).attachToRecyclerView(recyclerView)


    }

    /**
     * create option menu
     * */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.create_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.delete_all -> {
                /**
                 * deleting single word
                 * */
//                val word = Word("moses")
//                wordViewModel.delete(word)

                /**
                 * deleting whole rows
                 * */
                wordViewModel.deleteAll()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
