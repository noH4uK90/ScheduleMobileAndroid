package com.example.schedulemobileandroid.presentation.views.accountViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schedulemobile.R
import com.example.schedulemobileandroid.presentation.navigation.BottomNavItem
import com.example.schedulemobileandroid.presentation.navigation.ScheduleNavItem
import com.example.schedulemobileandroid.presentation.viewModels.AccountViewModel
import kotlinx.coroutines.launch

@Composable
fun AccountView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AccountViewModel
) {
    val scope = rememberCoroutineScope()
    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.padding(15.dp))
            Text(
                text = "${viewModel.account.value?.surname?.value ?: ""} ${viewModel.account.value?.name?.value ?: ""}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = viewModel.account.value?.email ?: "",
                fontSize = 10.sp,
                lineHeight = 2.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = modifier.padding(20.dp))

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

            ) {
                Column(
                    modifier = modifier
                        .padding(20.dp),
                ) {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Изменить пароль",
                            modifier = modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.logOut()
                             scope.launch {
                                navController.navigate(BottomNavItem.AccountScreen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            text = "Выход",
                            modifier = modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AccountViewPreview() {
//    AccountView()
//}