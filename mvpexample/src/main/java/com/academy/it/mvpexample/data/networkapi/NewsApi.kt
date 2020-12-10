package com.academy.it.mvpexample.data.networkapi

import com.academy.it.mvpexample.data.NewsData
import io.reactivex.rxjava3.core.Single

interface NewsApi {
    fun getTopHeadlines(country: String): Single<List<NewsData>>
}