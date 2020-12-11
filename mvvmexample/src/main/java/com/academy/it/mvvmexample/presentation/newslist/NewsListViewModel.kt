package com.academy.it.mvvmexample.presentation.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.it.mvvmexample.data.NewsData
import com.academy.it.mvvmexample.data.networkapi.NewsApi
import com.academy.it.mvvmexample.data.networkapi.NewsApiImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsListViewModel(
        private val newsApi: NewsApi = NewsApiImpl(),
        private val newsDataViewModelMapper: (List<NewsData>) -> List<NewsDataViewModel> = NewsDataViewModelMapper()
) : ViewModel() {

    private val mutableNewsDataViewModelLiveData = MutableLiveData<List<NewsDataViewModel>>()
    val newsDataViewModelLiveData: LiveData<List<NewsDataViewModel>> = mutableNewsDataViewModelLiveData

    private var disposable: Disposable? = null

    fun fetchNewsList() {
        disposable = newsApi.getTopHeadlines("US")
                .subscribeOn(Schedulers.computation())
                .map { data -> newsDataViewModelMapper(data) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { dataViewModelList ->
                            mutableNewsDataViewModelLiveData.value = dataViewModelList
                        },
                        {}
                )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}