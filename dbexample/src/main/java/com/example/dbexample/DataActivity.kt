package com.example.dbexample

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DataActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.buttonSave)
        button.setOnClickListener {
            val text = editText.text.toString()
            saveUser(text)
            finish()
        }
    }

    private fun saveUser(text: String) {
        val contentValues = ContentValues().apply {
            put("name", text)
        }
        (applicationContext as App)
                .dbHelper
                .writableDatabase
                .insert("users", null, contentValues)
    }

    companion object {
//        private const val KEY = "key"
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, DataActivity::class.java)
//        fun newIntent(context: Context, a: Int) = Intent(context, DataActivity::class.java).apply {
//            putExtra(KEY, a)
//        }
    }
}