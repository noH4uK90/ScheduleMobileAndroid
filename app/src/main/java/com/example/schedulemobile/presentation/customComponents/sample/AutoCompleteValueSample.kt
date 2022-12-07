package com.example.schedulemobile.presentation.customComponents.sample

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.example.schedulemobile.presentation.GroupState
import com.example.schedulemobile.presentation.customComponents.autoComplete.utils.AutoCompleteBox
import com.example.schedulemobile.presentation.customComponents.autoComplete.utils.asAutoCompleteEntities
import com.example.schedulemobile.presentation.customComponents.searchBar.TextSearchBar
import java.util.*

@Composable
fun AutoCompleteValueSample(groupState: GroupState) {

    groupState.groupList?.items?.let { groups ->
        val items = groups.map { it.name }

        val autoCompleteEntities = items.asAutoCompleteEntities(
            filter = { item, query ->
                item.toLowerCase(Locale.getDefault())
                    .startsWith(query.toLowerCase(Locale.getDefault()))
            }
        )

        AutoCompleteBox(
            items = autoCompleteEntities,
            itemContent = { item ->
                ValueAutoCompleteItem(item.value)
            }
        ) {
            var value by remember { mutableStateOf("") }
            val view = LocalView.current

            onItemSelected { item ->
                value = item.value
                filter(value)
                view.clearFocus()
            }

            TextSearchBar(
                modifier = Modifier,
                value = value,
                label = "Группа",
                onDoneActionClick = {
                    view.clearFocus()
                },
                onClearClick = {
                    value = ""
                    filter(value)
                    view.clearFocus()
                },
                onFocusChanged = { focusState ->
                    isSearching = focusState.isFocused == true
                },
                onValueChanged = { query ->
                    value = query
                    filter(value)
                }
            )
        }
    }
}

@Composable
fun ValueAutoCompleteItem(item: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item)
    }
}