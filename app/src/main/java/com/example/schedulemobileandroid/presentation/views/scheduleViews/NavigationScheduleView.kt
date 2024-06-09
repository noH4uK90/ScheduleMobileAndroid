package com.example.schedulemobileandroid.presentation.views.scheduleViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScheduleView(navController: NavController) {
    var selected by remember { mutableIntStateOf(0) }
    val tabs = mapOf(0 to "Группа", 1 to "Преподаватель")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selected,
            containerColor = Color.White
        ) {
            tabs.forEach { (index, tab) ->
                Tab(
                    selected = selected == index,
                    onClick = { selected = index },
                    text = { Text(tab) }
                )
            }
        }
        when (selected) {
            0 -> SelectGroupView(navController, hiltViewModel())
            1 -> SelectTeacherView(navController, hiltViewModel())
        }
    }
}

@Composable
fun Picker(selected: Int, onSelectedChange: (Int) -> Unit, tabs: Map<Int, String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        tabs.forEach { (index, tab) ->
            Button(
                onClick = { onSelectedChange(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected == index) Color.Blue else Color.Gray
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = tab, color = Color.White)
            }
        }
    }
}