package com.example.schedulemobileandroid.presentation.customComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CourseGroupsView(
    modifier: Modifier = Modifier
) {

    val itemsList = (0..10).toList()
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = modifier
                .padding(7.dp)
        ) {
            Text(
                text = "ИСПП",
                modifier = modifier
                    .padding(top = 4.dp),
                fontSize = 21.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        VerticalDivider(
            color = Color.Black,
            modifier = modifier
                .fillMaxHeight()
                .padding(vertical = 7.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.padding(7.dp)
        ) {
            items(itemsList) { group ->
                Card(
                    modifier = modifier
                        .padding(4.dp),
                    shape = RoundedCornerShape(7.dp)
                ) {
                    Text(
                        text = "${group}",
                        modifier = modifier
                            .padding(4.dp)
                            .padding(start = 3.dp)
                    )
                }
                /*Text(
                    text = "${group}",
                    modifier = Modifier
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(5.dp))
                        .border(0.3.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(5.dp))
                        .padding(4.dp)
                        .clickable {
                            // Здесь должен быть код для навигации, например:
                            // navController.navigate("groupSchedule/${group.id}")
                        },
                    color = MaterialTheme.colorScheme.onBackground,
                )*/
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CourseGroupsViewPreview() {
    CourseGroupsView()
}