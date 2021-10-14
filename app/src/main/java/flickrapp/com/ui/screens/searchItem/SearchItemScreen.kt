package flickrapp.com.ui.screens.searchItem

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchResult
import flickrapp.com.ui.previews.SearchResultDHPreview
import flickrapp.com.ui.screens.search.SearchScreen
import flickrapp.com.ui.theme.FlickrAppTheme

@Composable
fun SearchItemScreen(
    navController: NavHostController,
    searchResultDH: MutableState<DataHolder<SearchResult>>,
    itemId: String?
) {
    Scaffold {
        Text("SearchItemScreen $itemId")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(
    @PreviewParameter(SearchResultDHPreview::class)
    searchResultDH: MutableState<DataHolder<SearchResult>>

) {
    val navController = rememberNavController()

    FlickrAppTheme {
        SearchScreen(
            navController = navController,
            searchResultDH = searchResultDH
        )
    }
}
