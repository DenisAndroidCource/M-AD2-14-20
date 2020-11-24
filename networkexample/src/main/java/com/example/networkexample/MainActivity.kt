package com.example.networkexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networkexample.data.networkapi.NewsApiImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private val newsApi = NewsApiImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsApi.getTopHeadlines("US")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { dataList -> Log.d("DATA", dataList.toString()) },
                        { throwable -> Log.d("DATA", throwable.toString()) }
                )

    }
}