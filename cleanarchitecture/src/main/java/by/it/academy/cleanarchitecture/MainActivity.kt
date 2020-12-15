package by.it.academy.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.it.academy.cleanarchitecture.presentation.NewsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNewsListFragment()
    }

    private fun showNewsListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, NewsListFragment.newInstance())
            .commit()
    }
}