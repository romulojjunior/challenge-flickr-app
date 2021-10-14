package flickrapp.com.ui.previews

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchResult

class SearchResultDHPreview : PreviewParameterProvider<MutableState<DataHolder<SearchResult>>> {
    override val values = sequenceOf(
        mutableStateOf(
            DataHolder(
                data = SearchResult(
                    title = "Mocked Title",
                    link = "https://mocked-link.com/123123",
                    description = "Mocked Description",
                    items = emptyList()
                )),
        )
    )
}
