package flickrapp.com.domain.repository

import flickrapp.com.data.api.FlickrApiService
import flickrapp.com.data.local.AppDatabase
import flickrapp.com.data.local.entities.SearchTermEntity
import flickrapp.com.domain.models.SearchResult

class SearchRepository(
    private val apiService: FlickrApiService,
    private val localDatabase: AppDatabase
) {
    suspend fun searchByTags(tags: String): SearchResult {
        return apiService.searchByTags(tags = tags)
    }

    suspend fun saveSearchTerm(term: String): Long {
        return localDatabase.searchTermDAO().insert(SearchTermEntity(value = term))
    }

    suspend fun getRecentSearchTerms(): List<SearchTermEntity> {
        return localDatabase.searchTermDAO().getRecentSearchTerms()
    }

    suspend fun deleteOldSearchTermsUntilId(id: Long) {
        return localDatabase.searchTermDAO().deleteOldSearchTerms(id = id)
    }

    suspend fun deleteSearchTermByValue(value: String) {
        return localDatabase.searchTermDAO().deleteSearchTermByValue(value = value)
    }
}