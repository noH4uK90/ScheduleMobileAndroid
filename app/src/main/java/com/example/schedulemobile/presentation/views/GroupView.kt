package com.example.schedulemobile.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedulemobile.presentation.navigation.NavRoute
import com.example.schedulemobile.presentation.viewModels.GroupViewModel
import com.example.schedulemobile.presentation.viewModels.ObserverLifecycle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupView(
    navController: NavController,
    viewModel: GroupViewModel,
) {

    viewModel.ObserverLifecycle(LocalLifecycleOwner.current.lifecycle)

    val snackBarHostState = remember { SnackbarHostState() }

    if (viewModel.isSnackBarShowing) {
        LaunchedEffect(viewModel.isSnackBarShowing) {
            snackBarHostState.showSnackbar("Установлена некорректная группа")
            viewModel.isSnackBarShowing = false
        }
    }

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun closeSearchBar() {
        active = false
        focusManager.clearFocus()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            DockedSearchBar(
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
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable { closeSearchBar() }
                    )
                },
            ) {
                LazyColumn {
                    items(items = viewModel.state.groupList!!.items.filter {
                        it.name.contains(text, ignoreCase = true)
                    }) {
                        ListItem(
                            headlineText = { Text(it.name) },
                            modifier = Modifier.clickable {
                                text = it.name
                                closeSearchBar()
                            }
                        )
                        Divider()
                    }
                }
            }

            Button(
                modifier = Modifier
                    .padding(top = 10.dp),
                onClick = {
                    focusManager.clearFocus()
                    if (viewModel.validGroup(text)) navController.navigate(NavRoute.ScheduleScreen.route)
                    else viewModel.isSnackBarShowing = true
                },
                content = {
                    Text(text = "Сохранить")
                }
            )

            /*viewModel.state.groupList?.let { groups ->
                val items = groups.items.map { it.name }
                var value by rememberSaveable { mutableStateOf(viewModel.getSavedGroup()?.name ?: "") }

                val autoCompleteEntities = items.asAutoCompleteEntities(
                    filter = { item, query ->
                        item.lowercase(Locale.getDefault())
                            .startsWith(query.lowercase(Locale.getDefault()))
                    }
                )

                AutoCompleteBox(
                    items = autoCompleteEntities,
                    itemContent = { item ->
                        ValueAutoCompleteItem(item.value)
                    }
                ) {
                    onItemSelected { item ->
                        value = item.value
                        filter(value)
                        focusManager.clearFocus()
                    }

                    TextSearchBar(
                        modifier = Modifier,
                        value = value,
                        label = "Группа",
                        onDoneActionClick = {
                            focusManager.clearFocus()
                        },
                        onClearClick = {
                            value = ""
                            filter(value)
                            focusManager.clearFocus()
                        },
                        onFocusChanged = { focusState ->
                            isSearching = focusState.isFocused
                        },
                        onValueChanged = { query ->
                            value = query
                            filter(value)
                        }
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    onClick = {
                        focusManager.clearFocus()
                        if (viewModel.validGroup(value)) navController.navigate(NavRoute.ScheduleScreen.route)
                        else viewModel.isSnackBarShowing = true
                    },
                    content = {
                        Text(text = "Сохранить")
                    }
                )
            }*/
        }
    }
}