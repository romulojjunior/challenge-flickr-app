package flickrapp.com.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchResult
import flickrapp.com.ui.components.NoItem
import flickrapp.com.ui.components.TextAutoComplete
import flickrapp.com.ui.components.TryAgain
import flickrapp.com.ui.previews.SearchResultDHPreview
import flickrapp.com.ui.theme.FlickrAppTheme

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchResultDH: MutableState<DataHolder<SearchResult>>,
    onSearch: (query: String) -> Unit = {}
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TextAutoComplete(onSearch = {
                searchQuery = it
                onSearch(it)
            }, recentSearchTerms = listOf("Toronto", "City","Beach"))
        }
    ) {
        if (searchResultDH.value.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            if (searchResultDH.value.isNotInitialized()) {
                onSearch(searchQuery)
            }

            searchResultDH.value.exception?.let {
                item {
                    TryAgain {
                        onSearch(searchQuery)
                    }
                }
            }

            searchResultDH.value.data?.let { result ->
                if (result.items.isEmpty()) {
                    item {
                        NoItem()
                    }
                }

                items(result.items) { item ->
                    Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                        SearchItemCard(item = item, onClick = {
                            // TODO: Launch details screen
                        })
                    }
                }
            }
        }
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
