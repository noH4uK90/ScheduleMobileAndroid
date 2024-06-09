package com.example.schedulemobileandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.schedulemobileandroid.presentation.customComponents.CourseGroupsView
import com.example.schedulemobileandroid.presentation.customComponents.DayPicker
import com.example.schedulemobileandroid.presentation.navigation.BottomNavigationBar
import com.example.schedulemobileandroid.presentation.navigation.NavigationGraph
import com.example.schedulemobileandroid.presentation.ui.theme.ScheduleMobileTheme
import com.example.schedulemobileandroid.presentation.views.accountViews.AccountView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val groupViewModel: GroupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(context = this)
//            val navController = rememberNavController()
//            ScheduleMobileTheme {
//                Scaffold(
//                    Modifier.fillMaxSize(),
//                    bottomBar = { BottomNavigationBar(navController = navController, context = this) }
//                ) { paddingValues ->
                    //var startDestination = NavRoute.GroupScreen.route
//                    NavigationGraph(modifier = Modifier.padding(paddingValues), navController = navController, context = this)
                    //if (groupViewModel.getSavedGroup() != null) startDestination = NavRoute.ScheduleScreen.route
//                    MainNavigation(
//                        modifier = Modifier
//                            .padding(paddingValues),
//                        startDestination = startDestination
//                    )
//                }
//            }
        }
    }
}