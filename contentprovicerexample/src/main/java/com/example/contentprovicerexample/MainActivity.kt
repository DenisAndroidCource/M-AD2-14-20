package com.example.contentprovicerexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://www.it-academy.by/course/java-developer/jd1-programmirovanie-na-java/")
        }

        startActivity(intent)

        val cursor = contentResolver.query(
                Uri.parse("content://com.example.contentprovicerexample/data/data"),
                arrayOf("column1", "column2"),
                "age = ?",
                arrayOf("18"),
                "desc"
        )

        if (cursor != null) {
            while (cursor.moveToNext()) {

            }
            cursor.close()
        }
    }
}