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
import androidx.compose.ui.platform.LocalFocusManager
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
    var submited by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Card(elevation = 2.dp, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        TextField(
            value = textValue,
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    submited = true
                    onSearch(textValue)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            onValueChange = {
                textValue = it
                submited = false
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            },
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    submited = true
                    onSearch(textValue)
                }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
            }
        )

        val filteredItems = if (textValue.length == 1) {
            recentSearchTerms
        } else if (textValue.length > 1) {
            recentSearchTerms.filter { it.lowercase().contains(textValue.lowercase()) }
        } else {
            emptyList()
        }

        isExpanded = textValue.length == 1 || filteredItems.isNotEmpty()

        if (submited) {
            isExpanded = false
        }

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

            Column {
                filteredItems.forEach { item ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            focusManager.clearFocus()
                            onSearch(item)
                            textValue = item
                            isExpanded = false
                            submited = true
                        }) {
                        Text(item, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
                    }
                }
            }
        }
    }
}
