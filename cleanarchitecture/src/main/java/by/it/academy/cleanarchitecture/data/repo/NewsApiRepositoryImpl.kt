package by.it.academy.cleanarchitecture.data.repo

import by.it.academy.cleanarchitecture.api.NewsApiClient
import by.it.academy.cleanarchitecture.data.NewsDataModel
import by.it.academy.cleanarchitecture.domain.DomainModelMapper
import by.it.academy.cleanarchitecture.domain.NewsDomainModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsApiRepositoryImpl(
    private val apiClient: NewsApiClient = NewsApiClient(),
    private val domainModelMapperImpl: DomainModelMapper = DomainModelMapperImpl()
) : NewsApiRepository {

    override fun getTopHeadlines(country: String): Single<List<NewsDomainModel>> =
        Single.create<List<NewsDataModel>> { emitter ->
            emitter.onSuccess(apiClient.getTopNewsList(country))
        }.subscribeOn(Schedulers.io())
            .map<List<NewsDomainModel>> { data -> domainModelMapperImpl.toDomainModel(data) }
            .subscribeOn(Schedulers.computation())
}