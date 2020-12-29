package com.example.buildflavorsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("FLAVOR_EX", BuildConfig.API_DOMAIN)

        if (BuildConfig.isAnalyticsEnabled) {
            Log.d("FLAVOR_EX", "The analytics has been sent")
        } else {
            Log.d("FLAVOR_EX", "The analytics has NOT been sent")
        }

        findViewById<TextView>(R.id.textView).text = getCurrentDate()
    }
}