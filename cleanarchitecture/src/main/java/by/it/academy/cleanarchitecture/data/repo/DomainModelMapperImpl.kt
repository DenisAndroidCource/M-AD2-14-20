package by.it.academy.cleanarchitecture.data.repo

import by.it.academy.cleanarchitecture.data.NewsDataModel
import by.it.academy.cleanarchitecture.domain.DomainModelMapper
import by.it.academy.cleanarchitecture.domain.NewsDomainModel

class DomainModelMapperImpl : DomainModelMapper {
    override fun toDomainModel(dataModelList: List<NewsDataModel>) = dataModelList.map { item ->
        NewsDomainModel(
            title = item.title,
            description = item.description,
            url = item.url,
            urlToImage = item.urlToImage
        )
    }
}