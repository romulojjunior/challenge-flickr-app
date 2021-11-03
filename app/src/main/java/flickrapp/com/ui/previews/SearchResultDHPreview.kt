package flickrapp.com.ui.previews

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchItem
import flickrapp.com.domain.models.SearchMediaItem
import flickrapp.com.domain.models.SearchResult
import java.time.LocalDateTime

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
        ),
        mutableStateOf(
            DataHolder(
                data = SearchResult(
                    title = "Mocked Title",
                    link = "https://mocked-link.com/123123",
                    description = "Mocked Description",
                    items = listOf(
                        SearchItem(
                            title = "Item 01",
                            link = "https://www.google.com",
                            media = SearchMediaItem("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"),
                            dateTaken = LocalDateTime.now(),
                            description = "Simple description",
                            published = LocalDateTime.now(),
                            author = "James",
                            authorId = "1234",
                            tags = "nothing"
                        )
                    )
                )),
        )
    )
}
