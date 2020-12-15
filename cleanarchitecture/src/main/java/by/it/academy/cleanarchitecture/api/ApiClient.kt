package by.it.academy.cleanarchitecture.api

import by.it.academy.cleanarchitecture.data.NewsDataModel

interface ApiClient {
    fun getTopNewsList(country: String): List<NewsDataModel>
}