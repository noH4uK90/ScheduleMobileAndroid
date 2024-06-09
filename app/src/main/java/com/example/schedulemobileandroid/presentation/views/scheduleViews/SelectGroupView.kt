package com.example.schedulemobileandroid.presentation.views.scheduleViews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedulemobileandroid.presentation.viewModels.scheduleViewModels.SelectGroupViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SelectGroupView(
    navController: NavController,
    viewModel: SelectGroupViewModel
) {
    val groups by viewModel.groups.collectAsState()
    val search by viewModel.search.collectAsState()

    Column {
        OutlinedTextField(
            value = search,
            onValueChange = { viewModel.updateSearch(it) },
            placeholder = { Text("Группа") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn {
            items(groups) { group ->
                ListItem(
                    headlineContent = { Text(group.name) },
                    modifier = Modifier.clickable {
                        navController.navigate("groupSchedule/${group.id}")
                    }
                )
            }
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing.collectAsState().value),
            onRefresh = { viewModel.fetchGroups() }
        ) {
            // Content to refresh
        }
    }
}