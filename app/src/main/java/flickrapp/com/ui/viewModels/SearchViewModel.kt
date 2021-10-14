package flickrapp.com.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchResult
import flickrapp.com.domain.usecases.search.SearchByTagUseCase

class SearchViewModel(
    private val searchByTagUseCase: SearchByTagUseCase
) : BaseViewModel() {

    var searchResultDH = mutableStateOf<DataHolder<SearchResult>>(DataHolder())

    fun onSearch(query: String) {
        async(
            onStart = {
                searchResultDH.value = DataHolder(isLoading = true)
                searchByTagUseCase.execute(tags = query)
            },
            onComplete = { result ->
                searchResultDH.value = DataHolder(data = result)
            },
            onError = { e: Exception ->
                e.printStackTrace()
                searchResultDH.value = DataHolder(exception = e)
            }
        )
    }
}
