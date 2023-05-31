package com.example.schedulemobile.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedulemobile.presentation.navigation.NavRoute
import com.example.schedulemobile.presentation.viewModels.GroupViewModel
import com.example.schedulemobile.presentation.viewModels.ObserverLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupView(
    navController: NavController,
    viewModel: GroupViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    viewModel.ObserverLifecycle(lifecycle = lifecycleOwner.lifecycle)

    val snackBarHostState = remember { SnackbarHostState() }
    val isLoading = viewModel.state.isLoading
    val isSnackBarShowing = viewModel.isSnackBarShowing
    val groupList = viewModel.state.groupList

    LaunchedEffect(isSnackBarShowing) {
        if (isSnackBarShowing) {
            snackBarHostState.showSnackbar("Установлена некорректная группа")
            viewModel.isSnackBarShowing = false
        }
    }

    if (isLoading) {
        LoadingView()
    } else {
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
                        items(items = groupList?.items?.filter {
                            it.name.contains(text, ignoreCase = true)
                        } ?: emptyList()) {
                            ListItem(
                                headlineContent = { Text(it.name) },
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
            }
        }
    }
}