package flickrapp.com.ui.screens.searchItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.skydoves.landscapist.glide.GlideImage
import flickrapp.com.domain.models.DataHolder
import flickrapp.com.domain.models.SearchItem
import flickrapp.com.R
import flickrapp.com.ui.components.CardInfo
import flickrapp.com.ui.components.HTMLText
import flickrapp.com.ui.components.TextLink
import java.time.format.DateTimeFormatter

@Composable
fun SearchItemScreen(
    navController: NavHostController,
    searchItemDH: MutableState<DataHolder<SearchItem>>,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.details))
            }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            })
        }
    ) {
        if (searchItemDH.value.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        searchItemDH.value.exception?.let {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(id = R.string.item_not_found))
            }
        }

        LazyColumn {
            searchItemDH.value.data?.let { searchItem ->
                // Image
                item {
                    GlideImage(
                        imageModel = searchItem.media.m,
                        requestBuilder = Glide.with(LocalContext.current.applicationContext)
                            .asDrawable(),
                        modifier = Modifier.aspectRatio(1.77f)
                    )
                }

                // Title
                item {
                    CardInfo(
                        title = stringResource(id = R.string.title),
                        content = searchItem.title ?: stringResource(id = R.string.no_title)
                    )
                }

                // Author
                item {
                    CardInfo(
                        title = stringResource(id = R.string.author),
                        content = searchItem.author
                    )
                }

                // Tags
                item {
                    CardInfo(title = stringResource(id = R.string.tags), content = searchItem.tags)
                }

                // Tags
                item {
                    CardInfo(
                        title = stringResource(id = R.string.createdAt),
                        content = searchItem.dateTaken.format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")
                        )
                    )
                }

                // Description
                item {
                    Card(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.description),
                                style = MaterialTheme.typography.subtitle2
                            )
                            HTMLText(html = searchItem.description)
                        }
                    }
                }

                // URL
                item {
                    Row(modifier = Modifier.padding(16.dp)) {
                        TextLink(url = searchItem.link) {
                            Text(
                                text = stringResource(id = R.string.visit),
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

