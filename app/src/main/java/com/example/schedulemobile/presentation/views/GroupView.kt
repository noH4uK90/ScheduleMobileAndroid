package com.example.schedulemobile.presentation.views

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.presentation.customComponents.sample.AutoCompleteValueSample
import com.example.schedulemobile.presentation.viewModels.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupView(
    viewModel: GroupViewModel,
) {
    viewModel.loadGroups()

    Scaffold(
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
            AutoCompleteValueSample(groupState = viewModel.state)
            Button(
                modifier = Modifier
                    .padding(top = 10.dp),
                onClick = {},
                content = {
                    Text(text = "Сохранить")
                }
            )
        }
    }
}