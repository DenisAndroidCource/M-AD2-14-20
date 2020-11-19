package com.example.networkexample.data.networkapi

import okhttp3.*
import java.io.IOException

private const val TOP_HEADLINES_URL = "http://newsapi.org/v2/top-headlines?country=%s&category=business&apiKey=fe27628816ba4ca5b23fe932cf36e26e"

class NewsApiImpl: NewsApi {

    private val httpClient = OkHttpClient()

    override fun getTopHeadlines(country: String) {
        val request = Request.Builder().url(TOP_HEADLINES_URL.format(country)).build()
        httpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

            }
        })
    }
}