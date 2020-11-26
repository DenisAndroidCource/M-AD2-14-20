package com.example.networkexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.networkexample.presentation.newslist.NewsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNewsListFragment()
    }

    private fun showNewsListFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsListFragment())
                .commit()
    }
}