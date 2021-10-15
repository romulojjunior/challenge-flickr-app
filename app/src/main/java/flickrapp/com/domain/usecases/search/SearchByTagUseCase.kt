package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.models.SearchResult
import flickrapp.com.domain.repository.SearchRepository

class SearchByTagUseCase(
    private val searchRepository: SearchRepository
    ) {

    suspend fun execute(tags: String): SearchResult {
        if (tags.isBlank()) throw Exception("Invalid tag.")
        return searchRepository.searchByTags(tags = tags)
    }
}