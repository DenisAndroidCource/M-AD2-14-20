package com.academy.it.mvpexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.it.mvpexample.databinding.ActivityMainBinding
import com.academy.it.mvpexample.presentation.newslist.NewsListFragment
import com.academy.it.mvpexample.presentation.newsreader.FragmentNewsReader

interface OnNewsActionListener {
    fun openNews(url: String)
}

class MainActivity : AppCompatActivity(), OnNewsActionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        showNewsListFragment()

    }

    private fun showNewsListFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsListFragment.newInstance())
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