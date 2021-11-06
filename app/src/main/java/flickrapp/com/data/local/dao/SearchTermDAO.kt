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
    suspend fun getRecentSearchTerms(): List<SearchTermEntity>

    @Query("DELETE FROM search_terms WHERE id < :id")
    suspend fun deleteOldSearchTerms(id: Long)

    @Query("DELETE FROM search_terms WHERE value == :value")
    suspend fun deleteSearchTermByValue(value: String)
}
