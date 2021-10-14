package flickrapp.com.domain.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class SearchItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("media")
    val media: SearchMediaItem,
    @SerializedName("date_taken")
    val dateTaken: LocalDateTime,
    @SerializedName("description")
    val description: String,    // html field
    @SerializedName("published")
    val published: LocalDateTime,
    @SerializedName("author")
    val author: String, // format: "user@flickr.com (\"name1234\")"
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("tags")
    val tags: String
)
