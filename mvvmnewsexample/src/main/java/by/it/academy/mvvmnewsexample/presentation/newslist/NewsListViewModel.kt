package by.it.academy.mvvmnewsexample.presentation.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.it.academy.mvvmnewsexample.data.NewsData
import by.it.academy.mvvmnewsexample.data.networkapi.NewsApi
import by.it.academy.mvvmnewsexample.data.networkapi.NewsApiImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsListViewModel(
    private val newsApi: NewsApi = NewsApiImpl(),
    private val newsDataViewModelMapper: (List<NewsData>) -> List<NewsDataViewModel> = NewsDataViewModelMapper()
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<NewsDataViewModel>>()
    val liveData: LiveData<List<NewsDataViewModel>> = mutableLiveData

    private var disposable: Disposable? = null

//    fun getLiveData() : LiveData<List<NewsDataViewModel>> {
//        return mutableLiveData
//    }

    fun fetchNewsList() {
        disposable = newsApi.getTopHeadlines("US")
            .subscribeOn(Schedulers.computation())
            .map { data -> newsDataViewModelMapper(data) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataViewModelList -> mutableLiveData.value = dataViewModelList },
                {}
            )
    }
}