package flickrapp.com.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchItem
import flickrapp.com.domain.models.SearchResult
import flickrapp.com.domain.usecases.search.FilterSearchItemByIdUseCase
import flickrapp.com.domain.usecases.search.GetRecentSearchTermsUseCase
import flickrapp.com.domain.usecases.search.SaveSearchTermUseCase
import flickrapp.com.domain.usecases.search.SearchByTagUseCase

class SearchViewModel(
    private val searchByTagUseCase: SearchByTagUseCase,
    private val filterSearchItemByIdUseCase: FilterSearchItemByIdUseCase,
    private val saveSearchTermUseCase: SaveSearchTermUseCase,
    private val getRecentSearchTermsUseCase: GetRecentSearchTermsUseCase
) : BaseViewModel() {

    var searchResultDH = mutableStateOf<DataHolder<SearchResult>>(DataHolder())
    var selectedSearchItemDH = mutableStateOf<DataHolder<SearchItem>>(DataHolder())
    var recentSearchTerms = mutableStateOf(emptyList<String>())

    fun onSearch(query: String) {
        async(
            onStart = {
                searchResultDH.value = DataHolder(isLoading = true)
                saveSearchTermUseCase.execute(term = query)
                recentSearchTerms.value = getRecentSearchTermsUseCase.execute()
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

    fun selectSearchItem(itemId: String?) {
        async(
            onStart = {
                selectedSearchItemDH.value = DataHolder(isLoading = true)
                val searchItems: List<SearchItem> = searchResultDH.value.data?.items ?: emptyList()
                filterSearchItemByIdUseCase.execute(searchItems = searchItems, itemId = itemId)
            },
            onComplete = { result ->
                selectedSearchItemDH.value = DataHolder(data = result)
            },
            onError = { e: Exception ->
                e.printStackTrace()
                selectedSearchItemDH.value = DataHolder(exception = e)
            }
        )
    }
}
