package com.example.schedulemobileandroid.presentation.views.authViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedulemobile.R
import com.example.schedulemobileandroid.presentation.customComponents.SecureTextField
import com.example.schedulemobileandroid.presentation.navigation.BottomNavItem
import com.example.schedulemobileandroid.presentation.navigation.ScheduleNavItem
import com.example.schedulemobileandroid.presentation.viewModels.AuthState
import com.example.schedulemobileandroid.presentation.viewModels.AuthViewModel
import com.example.schedulemobileandroid.presentation.views.LoadingView

@Composable
fun AuthView(
    navController: NavController,
    viewModel: AuthViewModel
) {
    when (val authState = viewModel.authState) {
        is AuthState.Idle -> AuthForm(viewModel = viewModel)
        is AuthState.Loading -> LoadingView()
        is AuthState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(BottomNavItem.AccountScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }
        }
        is AuthState.Error -> Text((authState as AuthState.Error).message)
    }
}

@Composable
fun AuthForm(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

//    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = login,
                    onValueChange = { login = it },
                    placeholder = { Text("Логин") },
                    label = { Text(text = "Логин") }
                )
                SecureTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Пароль"
                )
            }

            Spacer(modifier = modifier.padding(vertical = 10.dp))

            Column(modifier = modifier.padding(0.dp)) {
                OutlinedButton(
                    onClick = { viewModel.logIn(login, password) },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(13.dp)
                ) {
                    Text("Войти")
                }
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text("Забыли пароль?")
                }
            }
        }
//    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AuthViewPreview() {
//    AuthView()
//}