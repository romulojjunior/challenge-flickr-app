package flickrapp.com.data.api

import flickrapp.com.domain.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/feeds/photos_public.gne?&nojsoncallback=1")
    suspend fun searchByTags(
        @Query("tags") tags: String,
        @Query("format") format: String = "json"
    ): SearchResult
}
