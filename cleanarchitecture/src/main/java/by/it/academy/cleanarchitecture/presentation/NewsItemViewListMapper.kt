package by.it.academy.cleanarchitecture.presentation

import by.it.academy.cleanarchitecture.domain.NewsDomainModel

class NewsItemViewListMapper : (List<NewsDomainModel>) -> List<NewsItemView> {
    override fun invoke(news: List<NewsDomainModel>): List<NewsItemView> =
        news.map { item ->
            NewsItemView(
                title = item.title,
                description = item.description,
                urlToImage = item.urlToImage,
                url = item.url
            )
        }
}