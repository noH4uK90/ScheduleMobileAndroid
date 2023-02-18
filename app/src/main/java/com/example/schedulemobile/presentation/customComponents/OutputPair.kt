package com.example.schedulemobile.presentation.customComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schedulemobile.domain.models.pair.Pair

@Composable
fun OutputPair(
    modifier: Modifier = Modifier,
    pair: Pair?
) {
    val color = if (pair?.changed == true) Color(red = 255, green = 0, blue = 0, alpha = 30) else MaterialTheme.colorScheme.surfaceVariant
    Column(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        DisciplineText(
            modifier = modifier
                .padding(vertical = 3.dp, horizontal = 5.dp)
                .background(color, RoundedCornerShape(10.dp)),
            discipline = pair?.discipline
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TeacherText(
                modifier = modifier
                    .padding(vertical = 3.dp, horizontal = 5.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .weight(2f),
                teacher = pair?.teacherClassrooms?.firstOrNull()?.teacher
            )
            CabinetText(
                modifier = modifier
                    .padding(vertical = 3.dp, horizontal = 5.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .weight(1f),
                cabinet = pair?.teacherClassrooms?.firstOrNull()?.classroom
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TeacherText(
                modifier = modifier
                    .padding(vertical = 3.dp, horizontal = 5.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .weight(2f),
                teacher = pair?.teacherClassrooms?.lastOrNull()?.teacher
            )
            CabinetText(
                modifier = modifier
                    .padding(vertical = 3.dp, horizontal = 5.dp)
                    .background(color, RoundedCornerShape(10.dp))
                    .weight(1f),
                cabinet = pair?.teacherClassrooms?.lastOrNull()?.classroom
            )
        }
        PairTimeText(
            modifier = modifier
                .padding(vertical = 3.dp, horizontal = 5.dp)
                .background(color, RoundedCornerShape(10.dp)),
            pairTime = pair?.time
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PairPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {paddingValues ->
        Card(
            modifier = Modifier
                .padding(horizontal = 50.dp, vertical = 80.dp)
                .padding(paddingValues)
        ) {
            LazyColumn(content = {
                items(1) {
                    //OutputPair()
                    //OutputPair()
                    //OutputPair()
                    //OutputPair()
                }
            })
        }
    }
}