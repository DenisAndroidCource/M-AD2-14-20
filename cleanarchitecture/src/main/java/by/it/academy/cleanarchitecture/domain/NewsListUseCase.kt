package by.it.academy.cleanarchitecture.domain

import io.reactivex.rxjava3.core.Single

interface NewsListUseCase {
    fun getNewsList(): Single<List<NewsDomainModel>>
}