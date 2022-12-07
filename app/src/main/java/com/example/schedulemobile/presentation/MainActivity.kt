package com.example.schedulemobile.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.schedulemobile.TimetableEpoxyController
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.repository.GroupRepository
import com.example.schedulemobile.presentation.customComponents.GroupDropdownMenu
import com.example.schedulemobile.presentation.customComponents.sample.AutoCompleteValueSample
import com.example.schedulemobile.presentation.ui.theme.ScheduleMobileTheme
import com.example.schedulemobile.presentation.viewModels.GroupViewModel
import com.example.schedulemobile.presentation.views.GroupView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val groupViewModel: GroupViewModel by viewModels()

    @Inject
    lateinit var groupRepository: GroupRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        groupViewModel.loadGroups()

        lifecycleScope.launchWhenStarted {
            //val response = timetableService.getAllTimetables()
            val response = groupRepository.getGroups().data!!
            Log.i("DATA", response.toString())
        }

        setContent {
            ScheduleMobileTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    Modifier.fillMaxSize()
                ) { paddingValues ->  
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        GroupView(viewModel = groupViewModel)
                    }
                }
            }
        }
    }
}