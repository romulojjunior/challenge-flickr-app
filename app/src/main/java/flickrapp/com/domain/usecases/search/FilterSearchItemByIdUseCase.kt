package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.models.SearchItem


class FilterSearchItemByIdUseCase {
    fun execute(searchItems: List<SearchItem>, itemId: String?): SearchItem {
        if (itemId == null) throw Exception("Invalid item Id")

        return searchItems.find { it.getId() == itemId } ?: throw Exception("Item not found")
    }
}