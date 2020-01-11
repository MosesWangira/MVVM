package com.example.arcitecturemvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arcitecturemvvm.Util.toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity()  {

    private lateinit var wordViewModel: WordViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
//            startActivityForResult(intent, newWordActivityRequestCode)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

//        val emptyView: View = findViewById(R.id.empty_catalog)


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
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                wordViewModel.delete(adapter.getWordAt(viewHolder.adapterPosition))
                //testing suspend functions
                toast("note deleted")
            } 
        }).attachToRecyclerView(recyclerView)

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            val wordy = data?.getStringExtra(NewWordActivity.EXTRA_WORD)
//            val titley = data?.getStringExtra(NewWordActivity.EXTRA_TITLE)
//
//            val word = Word(wordy!!, titley!!)
//            wordViewModel.insert(word)
//            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show()
//
//        } else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//    }

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

//                val intent = intent
//                val tit = intent.getStringExtra(NewWordActivity.EXTRA_REPLY1)


//                val extras = data.extras
//                val my_title = extras!!.getString("EXTRA_SEND")
//
//                val m = data.getStringExtra("EXTRA_SEND")
//                val title_of_note=intent.getStringExtra("TITLE")

//                val profileName=intent.getStringExtra("Username")
//                Toast.makeText(this@MainActivity, profileName, Toast.LENGTH_SHORT).show()
