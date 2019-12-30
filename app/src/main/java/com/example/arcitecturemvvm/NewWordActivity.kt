package com.example.arcitecturemvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editTitleView: EditText
    private lateinit var editUrgencyView: EditText

    private lateinit var wordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editWordView = findViewById(R.id.edit_word)
        editTitleView = findViewById(R.id.title_word)
        editUrgencyView = findViewById(R.id.urgency_word)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//        val adapter = WordListAdapter(this)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        wordViewModel.allWords.observe(this, Observer { words ->
//            // Update the cached copy of the words in the adapter.
//            words?.let { adapter.setWords(it) }
//        })


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if ((TextUtils.isEmpty(editWordView.text))) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "Can not save empty fields", Toast.LENGTH_SHORT).show()
            } else {
                val word = editWordView.text.toString()
                val title = editTitleView.text.toString()
                val urgent = editUrgencyView.text.toString()

                val urgentToInt = urgent.toInt()


                val myInsert = Word(word, title, urgentToInt)
                wordViewModel.insert(myInsert)
//                var db= Room.databaseBuilder(applicationContext,WordRoomDatabase::class.java,"WordDB").build()

//                val thread = Thread {
//                    var bookEntity = Word()
//                    bookEntity.title = "hello"
//                    bookEntity.word = "bye"
//                    db.wordDao().insert(bookEntity)

                    replyIntent.putExtra(EXTRA_REPLY, word)
//                replyIntent.putExtra(EXTRA_SEND, title)
//                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }


}

//addition to pass title data to main Activity
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("EXTRA_SEND", title)


//                val extras = Bundle()
//                extras.putString("Username", "john")
//                intent.putExtras(extras)
//                startActivity(replyIntent)

//                val intent = Intent(this, MainActivity::class.java)
//                val bundle = Bundle()
//                bundle.putSerializable("MOSES",title )
//                intent.putExtras(bundle)