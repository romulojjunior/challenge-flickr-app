package flickrapp.com.domain.models

import com.google.gson.annotations.SerializedName

class SearchResult(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("items")
    val items: List<SearchItem>
)
