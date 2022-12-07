package com.example.schedulemobile.presentation.customComponents.sample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.presentation.customComponents.autoComplete.utils.AutoCompleteBox
import com.example.schedulemobile.presentation.customComponents.searchBar.TextSearchBar

/*@Composable
fun AutoCompleteObjectSample(groups: GroupList) {
    AutoCompleteBox(
        items = groups.items,
        itemContent = { group ->
            GroupAutoCompleteItem(group)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { group ->
            value = group.name
            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier,
            value = value,
            label = "Search with objects",
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
}*/

/*
@Composable
fun GroupAutoCompleteItem(group: Group) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = group.name)
    }
}*/
