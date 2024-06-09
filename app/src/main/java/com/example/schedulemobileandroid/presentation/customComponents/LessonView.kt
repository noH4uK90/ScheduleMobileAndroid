package com.example.schedulemobileandroid.presentation.customComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.schedulemobileandroid.models.Lesson

//@Composable
//fun LessonView(
//    modifier: Modifier = Modifier,
//    //viewModel: LessonViewModel
//) {
//
//    Row(
//        modifier = modifier
//            .fillMaxSize()
//            .heightIn(max = 80.dp)
//            .padding(3.dp),
//        horizontalArrangement = Arrangement.Start
//    ) {
//        Column(
//            modifier = modifier
//                .fillMaxHeight()
//                .background(color = Color(0, 122, 255), shape = RoundedCornerShape(7.dp))
//                .padding(vertical = 3.dp, horizontal = 3.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("8:30", color = Color.White)
//            Text("10:10", color = Color.White)
//        }
//
//        VerticalDivider(
//            modifier = modifier
//                .padding(horizontal =  5.dp)
//        )
//
//        Column(
//            modifier = modifier
//                .fillMaxHeight(),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column {
//                Text(
//                    text = "Математика",
//                    fontWeight = FontWeight.Bold,
//                    maxLines = 1,
//                    lineHeight = 4.sp
//                )
//                Row {
//                    Text(
//                        text = "Иванов И.И. ",
//                        fontSize = 10.sp,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        lineHeight = 2.sp)
//                    Text(
//                        text = "Иванов И.И.",
//                        fontSize = 10.sp,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        lineHeight = 2.sp
//                    )
//                }
//            }
//
//            Row {
//                Text("407 ",
//                    modifier = modifier
//                        .padding(horizontal = 2.dp)
//                        .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(5.dp))
//                        .padding(horizontal = 5.dp, vertical = 1.dp),
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Center
//                )
//
//                Text("407 ",
//                    modifier = modifier
//                        .padding(horizontal = 2.dp)
//                        .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(5.dp))
//                        .padding(horizontal = 5.dp, vertical = 1.dp),
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
//}

@Composable
fun LessonView(lesson: Lesson) {
    Card(modifier = Modifier.padding(vertical = 3.dp)) {
        Content(lesson = lesson)
    }
}

@Composable
fun Content(lesson: Lesson) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CreateTime(lesson)
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Title(lesson)
            if (lesson.teacher?.fullName?.isNotEmpty() == true || lesson.subLesson != null) {
                Teacher(lesson)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (lesson.classroom?.cabinet?.isNotEmpty() == true || lesson.subLesson != null) {
                    Cabinet(lesson)
                }
                Text(
                    text = getShortLessonType(lesson.type.name),
                    modifier = Modifier.lessonTypeStyle(lesson.type.name),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun Title(lesson: Lesson) {
    Row {
        Text(
            text = getShortDisciplineName(lesson.discipline.name),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = lesson.discipline.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
    }
}

@Composable
fun Teacher(lesson: Lesson) {
    Row {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.padding(end = 5.dp)
        )
        Text(
            text = "${lesson.teacher?.fullName.orEmpty()} ${lesson.subLesson?.teacher?.fullName.orEmpty()}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun Cabinet(lesson: Lesson) {
    Row {
        Text(
            text = lesson.classroom?.cabinet.orEmpty(),
            modifier = Modifier.cabinetTextStyle(),
            color = Color.Blue.copy(alpha = 0.5f)
        )
        if (lesson.subLesson != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = lesson.subLesson.classroom?.cabinet.orEmpty(),
                modifier = Modifier.cabinetTextStyle(),
                color = Color.Blue.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun CreateTime(lesson: Lesson) {
    Column(
        modifier = Modifier
            .width(65.dp)
            .height(65.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(7.dp))
            .padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = lesson.timeStart,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        HorizontalDivider(
            modifier = Modifier
                .height(2.dp)
                .background(MaterialTheme.colorScheme.onPrimary)
        )
        Text(
            text = lesson.timeEnd,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun Modifier.cabinetTextStyle(): Modifier = this
    .padding(horizontal = 5.dp, vertical = 1.dp)
    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(5.dp))
    .padding(horizontal = 5.dp, vertical = 1.dp)

@Composable
fun Modifier.lessonTypeStyle(type: String): Modifier = this
    .background(getTypeColor(type), RoundedCornerShape(5.dp))
    .padding(horizontal = 5.dp, vertical = 1.dp)

fun getShortDisciplineName(name: String): String {
    if (name.length < 10) return ""
    val words = name.split(" ")
    if (words.size <= 2) return ""
    val result = words.joinToString("") { word ->
        if (word == "и") word else word.first().uppercase()
    }
    return "(${result.filter { it != '(' && it != ')' }})"
}

fun getShortLessonType(type: String): String {
    return when (type) {
        "Лекция" -> "лекц."
        "Практические занятия" -> "практ."
        "Лабораторная работа" -> "лаб."
        else -> ""
    }
}

fun getTypeColor(type: String): Color {
    return when (type) {
        "Лекция" -> Color.Blue.copy(alpha = 0.47f)
        "Практические занятия" -> Color.Green.copy(alpha = 0.47f)
        "Лабораторная работа" -> Color.Red.copy(alpha = 0.47f)
        else -> Color.Gray
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LessonViewPreview() {
//    LessonView()
//}