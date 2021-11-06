package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.repository.SearchRepository

class GetRecentSearchTermsUseCase(
    private val searchRepository: SearchRepository
    ) {

    suspend fun execute(): List<String> {
        return searchRepository.getRecentSearchTerms().map { it.value }
    }
}