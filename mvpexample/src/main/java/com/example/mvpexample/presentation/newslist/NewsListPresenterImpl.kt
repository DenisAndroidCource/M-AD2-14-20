package com.example.mvpexample.presentation.newslist

import androidx.lifecycle.MutableLiveData
import com.example.mvpexample.data.NewsData
import com.example.mvpexample.data.networkapi.NewsApi
import com.example.mvpexample.data.networkapi.NewsApiImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsListPresenterImpl(
    private var newsListView: NewsListView?,
    private val newsApi: NewsApi = NewsApiImpl(),
    private val newsDataViewModelMapper: (List<NewsData>) -> List<NewsDataViewModel> = NewsDataViewModelMapper()
) : NewsListPresenter {

    val liveData = MutableLiveData<List<NewsDataViewModel>>()

    private var disposable: Disposable? = null

    override fun fetchNewsList() {
        newsListView?.onStartLoading()
        disposable = newsApi.getTopHeadlines("US")
            .subscribeOn(Schedulers.computation())
            .map { data -> newsDataViewModelMapper(data) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataViewModelList ->
                    liveData.value = dataViewModelList
                    newsListView?.onStopLoading()
                    newsListView?.showNewsList(dataViewModelList)
                },
                {throwable ->
                    newsListView?.onError("Something went wrong")
                    newsListView?.onStopLoading()
                }
            )
    }

    override fun close() {
        newsListView = null
        disposable?.dispose()
    }
}