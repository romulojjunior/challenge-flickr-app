package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.models.SearchResult
import flickrapp.com.domain.repository.SearchRepository

class SearchByTagUseCase(
    private val searchRepository: SearchRepository
    ) {

    suspend fun execute(tags: String): SearchResult {
        return searchRepository.searchByTags(tags = tags)
    }
}