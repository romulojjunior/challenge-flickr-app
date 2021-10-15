package flickrapp.com.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun TextLink(url: String, label: @Composable (() -> Unit)) {
    val uriHandler = LocalUriHandler.current

    Row(modifier =Modifier.clickable {
        uriHandler.openUri(url)
    }) {
        label()
    }
}