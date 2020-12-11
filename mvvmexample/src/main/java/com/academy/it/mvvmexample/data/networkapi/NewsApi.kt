package com.academy.it.mvvmexample.data.networkapi

import com.academy.it.mvvmexample.data.NewsData
import io.reactivex.rxjava3.core.Single

interface NewsApi {
    fun getTopHeadlines(country: String): Single<List<NewsData>>
}