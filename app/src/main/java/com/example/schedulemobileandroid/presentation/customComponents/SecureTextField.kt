package com.example.schedulemobileandroid.presentation.customComponents

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.schedulemobile.R

@Composable
fun SecureTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    var valueVisibility by remember { mutableStateOf(false) }
    val icon = if (valueVisibility) {
        painterResource(id = R.drawable.ic_visibility)
    } else {
        painterResource(id = R.drawable.ic_visibility_off)
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        label = { Text(placeholder) },
        trailingIcon = {
            IconButton(onClick = { valueVisibility = !valueVisibility }) {
                Icon(painter = icon, contentDescription = "Show button")
            }
        },
        keyboardOptions =  KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (valueVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SecureTextFieldPreview() {
//    SecureTextField()
//}