package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.repository.SearchRepository

class SaveSearchTermUseCase(
    private val searchRepository: SearchRepository
    ) {

    suspend fun execute(term: String) {
        if (term.isBlank()) throw Exception("Invalid term.")
        if (term.contains(",")) {
            term.split(",").forEach {
                val termContent = it.trimMargin()
                searchRepository.deleteSearchTermByValue(value = termContent)
                searchRepository.saveSearchTerm(term = termContent)
            }
        } else {
            val termContent = term.trimMargin()
            searchRepository.deleteSearchTermByValue(value = termContent)
            searchRepository.saveSearchTerm(term = term)
        }
    }
}