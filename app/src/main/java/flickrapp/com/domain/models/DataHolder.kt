package flickrapp.com.domain.models

data class DataHolder<T>(
    var data: T? = null,
    var exception: Exception? = null,
    var isLoading: Boolean = false
) {

    fun isNotInitialized() = data == null && exception == null && !isLoading
}