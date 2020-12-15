package by.it.academy.cleanarchitecture.api

import by.it.academy.cleanarchitecture.data.NewsDataModel
import okhttp3.OkHttpClient
import okhttp3.ResponseBody

class NewsApiClient(
    private val httpClient: OkHttpClient = OkHttpClient(),
    private val requestFactory: RequestFactory = RequestFactoryImpl(),
    private val newsDataModelMapper: (String) -> List<NewsDataModel> = NewsDataListMapper()
) : ApiClient {
    override fun getTopNewsList(country: String): List<NewsDataModel> {
        val url = requestFactory.getTopHeadLinesRequest(country)
        val response = httpClient.newCall(url).execute()
        return if (response.body != null) {
            newsDataModelMapper((response.body as ResponseBody).string())
        } else {
            emptyList()
        }
    }
}