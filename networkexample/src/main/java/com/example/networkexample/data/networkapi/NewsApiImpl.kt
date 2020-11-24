package com.example.networkexample.data.networkapi

import com.example.networkexample.data.NewsData
import com.example.networkexample.data.NewsDataListMapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody

class NewsApiImpl(
        private val httpClient: OkHttpClient = OkHttpClient(),
        private val requestFactory: RequestFactory = RequestFactoryImpl(),
        private val dataMapper: (String) -> List<NewsData> = NewsDataListMapper()
) : NewsApi {

    override fun getTopHeadlines(country: String): Single<List<NewsData>> {
        val url = requestFactory.getTopHeadLinesRequest(country)
        return Single.create<String> { emitter ->
            val response = httpClient.newCall(url).execute()
            if (response.isSuccessful) {
                if (response.body != null) {
                    emitter.onSuccess((response.body as ResponseBody).string())
                } else {
                    emitter.onError(Throwable("EMPTY BODY"))
                }
            } else {
                emitter.onError(Throwable("API ERROR ${response.code}"))
            }
        }.map<List<NewsData>> { data -> dataMapper(data) }
                .subscribeOn(Schedulers.io())
    }
}