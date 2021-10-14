package flickrapp.com.domain.repository

import flickrapp.com.data.api.FlickrApiService
import flickrapp.com.domain.models.SearchResult

class SearchRepository(
    private val apiService: FlickrApiService
) {
    suspend fun searchByTags(tags: String): SearchResult {
        return apiService.searchByTags(tags = tags)
    }
}