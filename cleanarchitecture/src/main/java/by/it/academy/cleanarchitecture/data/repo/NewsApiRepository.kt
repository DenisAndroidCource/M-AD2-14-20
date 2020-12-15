package by.it.academy.cleanarchitecture.data.repo

import by.it.academy.cleanarchitecture.domain.NewsDomainModel
import io.reactivex.rxjava3.core.Single

interface NewsApiRepository {
    fun getTopHeadlines(country: String): Single<List<NewsDomainModel>>
}