package flickrapp.com.domain.models

object RouteNames {
    const val search = "search"
    const val searchItemDetails = "searchItemDetails"

    fun getSearchItemDetailsURL(itemId: String): String {
        return "${searchItemDetails}/$itemId"
    }
}