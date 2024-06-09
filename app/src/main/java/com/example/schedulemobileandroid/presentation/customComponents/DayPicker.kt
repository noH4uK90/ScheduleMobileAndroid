package com.example.schedulemobileandroid.presentation.customComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DayPicker(modifier: Modifier = Modifier, selectedDay: MutableState<Int>) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
        daysOfWeek.forEachIndexed { index, day ->
            val adjustedIndex = index + 1
            FilterChip(
                selected = selectedDay.value == adjustedIndex,
                onClick = { selectedDay.value = adjustedIndex; },
                label = { Text(day) },
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 11.dp, bottomStart = 11.dp)
                    daysOfWeek.size - 1 -> RoundedCornerShape(topEnd = 11.dp, bottomEnd = 11.dp)
                    else -> RoundedCornerShape(0.dp)
                }
            )
        }
    }
}

//@Preview
//@Composable
//fun DayPickerPreview() {
//    DayPicker()
//}