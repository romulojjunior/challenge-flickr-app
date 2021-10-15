package flickrapp.com.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardInfo(title: String, content: String) {
    Card(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(text = title, style = MaterialTheme.typography.subtitle2)
            Text(text = content, style = MaterialTheme.typography.body2)
        }
    }
}