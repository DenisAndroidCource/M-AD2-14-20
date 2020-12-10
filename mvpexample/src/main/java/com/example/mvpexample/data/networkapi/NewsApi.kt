package com.example.mvpexample.data.networkapi

import com.example.mvpexample.data.NewsData
import io.reactivex.rxjava3.core.Single

interface NewsApi {
    fun getTopHeadlines(country: String): Single<List<NewsData>>
}