package com.example.schedulemobileandroid.presentation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.schedulemobileandroid.presentation.navigation.BottomNavigationBar
import com.example.schedulemobileandroid.presentation.navigation.NavigationGraph
import com.example.schedulemobileandroid.presentation.ui.theme.ScheduleMobileTheme
import com.example.schedulemobileandroid.services.LocalNavigationService
import com.example.schedulemobileandroid.services.NavigationService

@Composable
fun MyApp(context: Context) {
     val navigationService: NavigationService = hiltViewModel()

    CompositionLocalProvider(LocalNavigationService provides navigationService) {
        val navController = rememberNavController()

        ScheduleMobileTheme {
            Scaffold(
                Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigationBar(navController = navController, context = context)
                }
            ) { paddingValues ->
                NavigationGraph(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
                    context = context
                )
            }
        }
    }
}