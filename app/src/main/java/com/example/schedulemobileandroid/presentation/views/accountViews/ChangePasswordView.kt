package com.example.schedulemobileandroid.presentation.views.accountViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schedulemobileandroid.presentation.customComponents.SecureTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordView(
    modifier: Modifier = Modifier
) {

    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SecureTextField(
                value = oldPassword,
                modifier = modifier
                    .fillMaxWidth(),
                onValueChange = { oldPassword = it },
                placeholder = "Старый пароль"
            )

            Spacer(modifier = modifier.padding(10.dp))

            SecureTextField(
                value = oldPassword,
                modifier = modifier
                    .fillMaxWidth(),
                onValueChange = { oldPassword = it },
                placeholder = "Новый пароль"
            )
            SecureTextField(
                value = oldPassword,
                modifier = modifier
                    .fillMaxWidth(),
                onValueChange = { oldPassword = it },
                placeholder = "Подтверждение пароля"
            )

            Spacer(modifier = modifier.padding(20.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Изменить")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChangePasswordViewPreview() {
    ChangePasswordView()
}