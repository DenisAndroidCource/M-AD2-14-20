package by.it.academy.cleanarchitecture.api

import okhttp3.Request

private const val API_KEY = "fe27628816ba4ca5b23fe932cf36e26e"
private const val TOP_HEADLINES_URL = "https://newsapi.org/v2/top-headlines?country=%s&category=business&apiKey=%s"

class RequestFactoryImpl : RequestFactory {
    override fun getTopHeadLinesRequest(country: String): Request {
        val url = TOP_HEADLINES_URL.format(country, API_KEY)
        return Request.Builder().url(url).build()
    }
}