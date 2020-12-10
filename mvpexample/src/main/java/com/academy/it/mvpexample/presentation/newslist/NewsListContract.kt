package com.academy.it.mvpexample.presentation.newslist

interface NewsListPresenter {
    fun fetchNewsList()
    fun close()
}

interface NewsListView {
    fun onStartLoading()
    fun onStopLoading()
    fun showNewsList(newsList: List<NewsDataViewModel>)
    fun onError(message: String)
}

class NewsDataViewModel(
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String
)