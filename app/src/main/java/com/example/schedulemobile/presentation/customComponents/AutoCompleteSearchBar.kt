package com.example.schedulemobile.presentation.customComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.schedulemobile.presentation.GroupState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteSearchBar(state: GroupState?) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun closeSearchBar() {
        focusManager.clearFocus()
        active = false
    }

    /*Box(Modifier.fillMaxSize()) {
        // This is okay to put first because search bars by default have zIndex = 1f.
        DockedSearchBar(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp),
            query = text,
            onQueryChange = { text = it },
            onSearch = { closeSearchBar() },
            active = active,
            onActiveChange = {
                active = it
                if (!active) focusManager.clearFocus()
            },
            placeholder = { Text("Hinted search text") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(4) { idx ->
                    val resultText = "Suggestion $idx"
                    @OptIn(ExperimentalMaterial3Api::class)
                    ListItem(
                        headlineText = { Text(resultText) },
                        supportingText = { Text("Additional info") },
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                        modifier = Modifier.clickable {
                            text = resultText
                            closeSearchBar()
                        }
                    )
                    if (idx != 3) { Divider() }
                }
            }
        }
    }*/

    SearchBar(
        modifier = Modifier
            .heightIn(20.dp)
            .padding(top = 8.dp),
        query = text,
        onQueryChange = { text = it },
        onSearch = { closeSearchBar() },
        active = active,
        onActiveChange = {
            active = it
            if (!active) focusManager.clearFocus()
        },
        placeholder = { Text("Группа") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
    ) {
        LazyColumn {
            if (state != null) {
                items(items = state.groupList!!.items.filter {
                    it.name.contains(text, ignoreCase = true)
                }) {
                    ListItem(
                        headlineContent = { Text(it.name) },
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                        modifier = Modifier.clickable {
                            text = it.name
                            closeSearchBar()
                        }
                    )
                    Divider()
                }
            }
        }
    }
}