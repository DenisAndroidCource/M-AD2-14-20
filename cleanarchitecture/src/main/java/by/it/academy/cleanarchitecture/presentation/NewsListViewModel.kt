package by.it.academy.cleanarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.it.academy.cleanarchitecture.domain.NewsDomainModel
import by.it.academy.cleanarchitecture.domain.NewsListUseCase
import by.it.academy.cleanarchitecture.domain.NewsListUseCaseImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsListViewModel(
    private val newsListUseCase: NewsListUseCase = NewsListUseCaseImpl(),
    private val newsItemViewListMapper: (List<NewsDomainModel>) -> List<NewsItemView> = NewsItemViewListMapper()
) : ViewModel() {

    private val mutableNewsItemListLiveData = MutableLiveData<List<NewsItemView>>()
    val newsItemListLiveData: LiveData<List<NewsItemView>> = mutableNewsItemListLiveData

    private var disposable: Disposable? = null

    fun fetchNewsList() {
        newsListUseCase.getNewsList()
            .subscribeOn(Schedulers.computation())
            .map { domainModelList -> newsItemViewListMapper(domainModelList) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> mutableNewsItemListLiveData.value = items }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}