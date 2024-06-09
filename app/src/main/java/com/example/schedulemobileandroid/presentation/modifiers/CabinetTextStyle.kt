package com.example.schedulemobileandroid.presentation.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.cabinetTextStyle(): Modifier {
    return this
        .padding(horizontal = 5.dp, vertical = 1.dp)
        .background(Color.LightGray)
        .clip(RoundedCornerShape(5.dp))
        .border(1.dp, Color.LightGray, RoundedCornerShape(5.dp))
}