package by.it.academy.cleanarchitecture.domain

class MultipleNewsListDataModelMapper :
    (List<NewsDomainModel>, List<NewsDomainModel>, List<NewsDomainModel>) -> List<NewsDomainModel> {
    override fun invoke(
        usNews: List<NewsDomainModel>,
        frNews: List<NewsDomainModel>,
        deNews: List<NewsDomainModel>
    ): List<NewsDomainModel> = mutableListOf<NewsDomainModel>().apply {
        addAll(usNews)
        addAll(frNews)
        addAll(deNews)
    }
}