package com.example.schedulemobileandroid.presentation.navigation

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schedulemobile.R
import com.example.schedulemobileandroid.models.Account
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.presentation.viewModels.AccountViewModel
import com.example.schedulemobileandroid.presentation.viewModels.AuthViewModel
import com.example.schedulemobileandroid.presentation.viewModels.ScheduleViewModel
import com.example.schedulemobileandroid.presentation.views.LoadingView
import com.example.schedulemobileandroid.presentation.views.accountViews.AccountView
import com.example.schedulemobileandroid.presentation.views.authViews.AuthView
import com.example.schedulemobileandroid.presentation.views.scheduleViews.GroupScheduleView
import com.example.schedulemobileandroid.presentation.views.scheduleViews.NavigationScheduleView
import com.example.schedulemobileandroid.presentation.views.scheduleViews.TeacherScheduleView
import com.example.schedulemobileandroid.services.LocalNavigationService
import com.google.gson.Gson

//@Composable
//fun MainNavigation(
//    modifier: Modifier = Modifier,
////    groupViewModel: GroupViewModel = hiltViewModel(),
//    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
//    navController: NavHostController = rememberNavController(),
//    startDestination: String,
//) {
//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = startDestination
//    ) {
//        composable(NavRoute.GroupScreen.route) {
//            BackHandler(true) {
//            }
////            GroupView(navController = navController, viewModel = groupViewModel)
//        }
//
//        composable(NavRoute.ScheduleScreen.route) {
//            BackHandler(true) {
//            }
//            //ScheduleView(navController = navController, viewModel = scheduleViewModel)
//        }
//    }
//}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomNavigationBar(navController: NavHostController, context: Context) {
    val navigationService = LocalNavigationService.current
    val isAuthenticated by navigationService.isAuthenticated.collectAsState()

    val items = if (isAuthenticated) {
        listOf(
            BottomNavItem.ScheduleScreen,
            BottomNavItem.MyScheduleScreen,
            BottomNavItem.HomeworkScreen,
            BottomNavItem.AccountScreen
        )
    } else {
        listOf(
            BottomNavItem.ScheduleScreen,
            BottomNavItem.AccountScreen
        )
    }
    NavigationBar {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), item.title)
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(modifier: Modifier = Modifier,navController: NavHostController, context: Context) {
    val navigationService = LocalNavigationService.current
    val isAuthenticated by navigationService.isAuthenticated.collectAsState()
    val account by navigationService.account.collectAsState()
    val group by navigationService.group.collectAsState()
    val teacherFullName by navigationService.teacherFullName.collectAsState()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomNavItem.ScheduleScreen.route
    ) {
        composable(BottomNavItem.ScheduleScreen.route) {
            NavigationScheduleView(navController)
        }
        composable(BottomNavItem.MyScheduleScreen.route) {
            if (account != null) {
                when(account?.role?.name) {
                    "Teacher" -> TeacherScheduleView(teacherId = teacherFullName?.id ?: 0)
                    "Student" -> GroupScheduleView(groupId = group?.id ?: 0)
                }
            }
        }
        composable(BottomNavItem.HomeworkScreen.route) {

        }
        composable(BottomNavItem.AccountScreen.route) {
            if (isAuthenticated) {
                val viewModel = hiltViewModel<AccountViewModel>()
                AccountView(navController = navController, viewModel = viewModel)
            } else {
                val viewModel = hiltViewModel<AuthViewModel>()
                AuthView(navController, viewModel)
            }
        }
        
        composable("groupSchedule/{groupId}") { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")?.toInt() ?: 0
            GroupScheduleView(groupId = groupId)
        }
        
        composable("teacherSchedule/{teacherId}") { backStackEntry -> 
            val teacherId = backStackEntry.arguments?.getString("teacherId")?.toInt() ?: 0
            TeacherScheduleView(teacherId = teacherId)
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: Int, val route: String) {
    data object ScheduleScreen: BottomNavItem("Расписание", R.drawable.ic_book_2,"scheduleScreen")
    data object MyScheduleScreen: BottomNavItem("Мое расписание", R.drawable.ic_book_3,"myScheduleScreen")
    data object HomeworkScreen: BottomNavItem("Домашнее задание", R.drawable.homework,"homeworkScreen")
    data object AccountScreen: BottomNavItem("Аккаунт", R.drawable.account_circle,"accountScreen")
}

sealed class ScheduleNavItem(val route: String) {
    data object ScheduleNavigation: ScheduleNavItem("scheduleNavigationScreen")
    data object TeacherSchedule: ScheduleNavItem("teacherScheduleScreen")
    data object GroupSchedule: ScheduleNavItem("groupScheduleScreen")
    data object AccountScreen: ScheduleNavItem("accountScreen")
    data object AuthScreen: ScheduleNavItem("authScreen")
}