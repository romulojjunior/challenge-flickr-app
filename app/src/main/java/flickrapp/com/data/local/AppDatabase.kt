package flickrapp.com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import flickrapp.com.data.local.dao.SearchTermDAO
import flickrapp.com.data.local.entities.SearchTermEntity

@Database(entities = [SearchTermEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchTermDAO(): SearchTermDAO
}
