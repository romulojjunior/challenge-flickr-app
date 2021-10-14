package flickrapp.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import flickrapp.com.domain.models.RouteNames
import flickrapp.com.ui.screens.search.SearchScreen
import flickrapp.com.ui.theme.FlickrAppTheme
import flickrapp.com.ui.viewModels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            FlickrAppTheme {
                NavHost(navController = navController, startDestination = RouteNames.search) {
                    composable(route = RouteNames.search) {
                        val searchViewModel by viewModel<SearchViewModel>()

                        SearchScreen(
                            navController = navController,
                            searchResultDH = searchViewModel.searchResultDH,
                            onSearch = searchViewModel::onSearch
                        )
                    }
                }
            }
        }
    }
}
