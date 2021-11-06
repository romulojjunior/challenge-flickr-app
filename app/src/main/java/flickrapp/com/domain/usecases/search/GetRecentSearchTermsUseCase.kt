package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.repository.SearchRepository

class GetRecentSearchTermsUseCase(
    private val searchRepository: SearchRepository
    ) {

    suspend fun execute(): List<String> {
        val entities = searchRepository.getRecentSearchTerms()

        if (entities.count() == 5) {
            entities.last().id?.let { entityId ->
                searchRepository.deleteOldSearchTermsUntilId(entityId)
            }
        }
        return searchRepository.getRecentSearchTerms().map { it.value }
    }
}