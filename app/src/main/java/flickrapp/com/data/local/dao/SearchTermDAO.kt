package flickrapp.com.data.local.dao

import androidx.room.*
import flickrapp.com.data.local.entities.SearchTermEntity

@Dao
interface SearchTermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SearchTermEntity): Long

    @Delete
    suspend fun delete(entity: SearchTermEntity)

    @Query("SELECT * FROM search_terms ORDER BY  id DESC LIMIT 5")
    fun getRecentSearchTerms(): List<SearchTermEntity>
}