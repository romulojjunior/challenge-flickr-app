package flickrapp.com.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
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
import flickrapp.com.domain.models.RouteNames
import flickrapp.com.domain.models.SearchResult
import flickrapp.com.ui.components.NoItem
import flickrapp.com.ui.components.TextAutoComplete
import flickrapp.com.ui.components.TryAgain
import flickrapp.com.ui.previews.SearchResultDHPreview
import flickrapp.com.ui.theme.FlickrAppTheme

@ExperimentalFoundationApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchResultDH: MutableState<DataHolder<SearchResult>>,
    recentSearchTerms: MutableState<List<String>>,
    onSearch: (query: String) -> Unit = {}
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TextAutoComplete(onSearch = {
                searchQuery = it
                onSearch(it)
            }, recentSearchTerms = recentSearchTerms.value)
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

        searchResultDH.value.exception?.let {
            TryAgain {
                onSearch(searchQuery)
            }
        }

        searchResultDH.value.data?.let { result ->
            if (result.items.isEmpty()) {
                NoItem()
            }

            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(result.items) { item ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        SearchItemCard(item = item, onClick = {
                            navController.navigate(RouteNames.getSearchItemDetailsURL(itemId = item.getId()))
                        })
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview(
    @PreviewParameter(SearchResultDHPreview::class)
    searchResultDH: MutableState<DataHolder<SearchResult>>

) {
    val navController = rememberNavController()
    val recentSearchTerms = remember {
        mutableStateOf(listOf("Toronto"))
    }

    FlickrAppTheme {
        SearchScreen(
            navController = navController,
            searchResultDH = searchResultDH,
            recentSearchTerms = recentSearchTerms
        )
    }
}
