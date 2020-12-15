package by.it.academy.cleanarchitecture.domain

import by.it.academy.cleanarchitecture.data.NewsDataModel

interface DomainModelMapper {
    fun toDomainModel(dataModelList: List<NewsDataModel>): List<NewsDomainModel>
}