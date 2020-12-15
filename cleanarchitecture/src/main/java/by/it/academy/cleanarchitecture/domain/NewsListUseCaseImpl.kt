package by.it.academy.cleanarchitecture.domain

import by.it.academy.cleanarchitecture.data.repo.NewsApiRepository
import by.it.academy.cleanarchitecture.data.repo.NewsApiRepositoryImpl
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function3

class NewsListUseCaseImpl(
    private val dataRepository: NewsApiRepository = NewsApiRepositoryImpl(),
    private val newsListMapper:
    ((List<NewsDomainModel>, List<NewsDomainModel>, List<NewsDomainModel>) -> List<NewsDomainModel>) =
        MultipleNewsListDataModelMapper()
) : NewsListUseCase {

    override fun getNewsList(): Single<List<NewsDomainModel>> = Single.zip(
        dataRepository.getTopHeadlines("us"),
        dataRepository.getTopHeadlines("fr"),
        dataRepository.getTopHeadlines("de"),
        Function3(newsListMapper)
    )
}