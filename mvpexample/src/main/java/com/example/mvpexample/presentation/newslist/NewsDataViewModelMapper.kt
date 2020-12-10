package com.example.mvpexample.presentation.newslist

import com.example.mvpexample.data.NewsData

class NewsDataViewModelMapper : (List<NewsData>) -> List<NewsDataViewModel> {
    override fun invoke(newsDataList: List<NewsData>) = newsDataList.map { item ->
        NewsDataViewModel(
            title = item.title,
            description = item.description,
            url = item.url,
            urlToImage = item.urlToImage
        )
    }
}