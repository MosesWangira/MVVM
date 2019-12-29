package com.example.arcitecturemvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editTitleView: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editWordView = findViewById(R.id.edit_word)
        editTitleView = findViewById(R.id.title_word)



        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if ((TextUtils.isEmpty(editWordView.text))) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "Can not save empty fields", Toast.LENGTH_SHORT).show()
            } else {
                val word = editWordView.text.toString()
                val title = editWordView.text.toString()

                //addition to pass title data to main Activity
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra(EXTRA_REPLY1, title)


//                val extras = Bundle()
//                extras.putString("EXTRA_TITLE", title)
//                intent.putExtras(extras)
//                startActivity(intent)

//                val intent = Intent(this, MainActivity::class.java)
//                val bundle = Bundle()
//                bundle.putSerializable("MOSES",title )
//                intent.putExtras(bundle)


                replyIntent.putExtra(EXTRA_REPLY, word)
                replyIntent.putExtra(EXTRA_SEND, title)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_SEND = "com.example.android.wordlistsql.REPLY"
    }
}
