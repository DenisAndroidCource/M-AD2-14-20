package by.it.academy.mvvmnewsexample.data.networkapi

import by.it.academy.mvvmnewsexample.data.NewsData
import io.reactivex.rxjava3.core.Single

interface NewsApi {
    fun getTopHeadlines(country: String): Single<List<NewsData>>
}