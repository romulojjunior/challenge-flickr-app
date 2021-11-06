package flickrapp.com.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_terms")
data class SearchTermEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var value: String
)