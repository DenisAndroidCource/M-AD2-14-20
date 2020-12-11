package com.academy.it.mvvmexample.data

import org.json.JSONObject

class NewsDataListMapper : (String) -> List<NewsData> {
    override fun invoke(json: String): List<NewsData> {
        val jsonMainObject = JSONObject(json)
        val articles = jsonMainObject.getJSONArray("articles")
        val list = mutableListOf<NewsData>()
        for (i in 0 until articles.length()) {
            val jsonArticle = articles.getJSONObject(i)
            val newsData = NewsData(
                    title = jsonArticle.getString("title"),
                    description = jsonArticle.getString("description"),
                    url = jsonArticle.getString("url"),
                    urlToImage = jsonArticle.getString("urlToImage")
            )
            list.add(newsData)
        }
        return list
    }
}