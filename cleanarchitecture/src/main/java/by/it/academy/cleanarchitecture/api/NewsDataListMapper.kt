package by.it.academy.cleanarchitecture.api

import by.it.academy.cleanarchitecture.data.NewsDataModel
import org.json.JSONObject

class NewsDataListMapper : (String) -> List<NewsDataModel> {
    override fun invoke(json: String): List<NewsDataModel> {
        val jsonMainObject = JSONObject(json)
        val articles = jsonMainObject.getJSONArray("articles")
        val list = mutableListOf<NewsDataModel>()
        for (i in 0 until articles.length()) {
            val jsonArticle = articles.getJSONObject(i)
            val newsData = NewsDataModel(
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