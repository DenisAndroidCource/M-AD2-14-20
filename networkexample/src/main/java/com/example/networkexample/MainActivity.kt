package com.example.networkexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.networkexample.databinding.ActivityMainBinding
import com.example.networkexample.presentation.newslist.FragmentNewsReader
import com.example.networkexample.presentation.newslist.NewsListFragment

interface onNewsActionListener {
    fun openNews(url: String)
}

class MainActivity : AppCompatActivity(), onNewsActionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        showNewsListFragment()

    }

    private fun showNewsListFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsListFragment())
                .commit()
    }

    private fun showFragmentNewsReader(url: String) {
        if (binding.fragmentContainer2 != null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer2)
            if (fragment != null) {
                (fragment as FragmentNewsReader).loadUrl(url)
            } else {
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainer2, FragmentNewsReader.newInstance(url))
                        .addToBackStack(null)
                        .commit()
            }
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, FragmentNewsReader.newInstance(url))
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun openNews(url: String) {
        showFragmentNewsReader(url)
    }
}