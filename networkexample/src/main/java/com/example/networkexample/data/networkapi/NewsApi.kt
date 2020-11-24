package com.example.networkexample.data.networkapi

import com.example.networkexample.data.NewsData
import io.reactivex.rxjava3.core.Single

interface NewsApi {
    fun getTopHeadlines(country: String): Single<List<NewsData>>
}