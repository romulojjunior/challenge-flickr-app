package flickrapp.com.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.skydoves.landscapist.glide.GlideImage
import flickrapp.com.domain.models.SearchItem

@Composable
fun  SearchItemCard(item: SearchItem, onClick: (SearchItem) -> Unit = {}) {
    Card(modifier = Modifier.clickable { onClick(item) }) {
        Column {
            GlideImage(
                imageModel = item.media.m,
                requestBuilder = Glide.with(LocalContext.current.applicationContext).asDrawable(),
                modifier = Modifier.aspectRatio(1.77f))
            Text(text = item.title, modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(vertical = 8.dp))
        }
    }
}
