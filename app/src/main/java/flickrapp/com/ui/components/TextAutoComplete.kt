package flickrapp.com.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import flickrapp.com.R

@Composable
fun TextAutoComplete(
    onSearch: (query: String) -> Unit = {},
    recentSearchTerms: List<String> = emptyList()
) {
    var textValue by rememberSaveable { mutableStateOf("") }
    var isExpanded by rememberSaveable { mutableStateOf(false) }


    Card(elevation = 2.dp, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        stringResource(id = R.string.search)

        TextField(
            value = textValue,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(textValue) }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            onValueChange = {
                textValue = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            },
            trailingIcon = {
                IconButton(onClick = { onSearch(textValue) }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
            }
        )

        isExpanded = textValue.length == 1

        DropdownMenu(
            expanded = isExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Column() {
                recentSearchTerms.forEach { item ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSearch(item)
                            textValue = item
                            isExpanded = false
                        }) {
                        Text(item, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
                    }
                }
            }
        }
    }
}
