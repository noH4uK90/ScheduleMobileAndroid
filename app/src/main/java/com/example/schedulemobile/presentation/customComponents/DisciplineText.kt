package com.example.schedulemobile.presentation.customComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DisciplineText(discipline: MutableState<String>) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val teacherText = createRef()

        Text(
            text = discipline.value,
            modifier = Modifier
                .constrainAs(teacherText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
        )
    }
}