package com.example.schedulemobile.presentation.customComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schedulemobile.domain.models.classroom.Classroom
import com.example.schedulemobile.domain.models.classroomType.ClassroomType
import com.example.schedulemobile.domain.models.discipline.Discipline
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.teacher.Teacher
import com.example.schedulemobile.domain.models.teacherClassroom.TeacherClassroom

@Composable
fun CabinetText(
    modifier: Modifier = Modifier,
    cabinet: Classroom?
) {
    Column(
        modifier = modifier
            .height(40.dp)
            .border(width = 2.dp,
                brush = SolidColor(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = cabinet?.cabinet ?: "",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
            )
        )
    }
}

@Preview
@Composable
fun CabinetTextPreview() {
    val disciplines: List<Discipline> = List(1) { Discipline(1, "") }
    val groups: List<Group> = List(1) { Group(1, "", 1, disciplines) }
    val types: List<ClassroomType> = List(1) { ClassroomType(1, "") }
    val teacher = Teacher(1, "", "", "teacher", groups = groups, disciplines)
    val teacher2 = Teacher(1, "", "", "teacher2", groups = groups, disciplines)
    val classroom = Classroom(1, "0109", types)
    val classroom2 = Classroom(1, "0111", types)
    val teacherClassrooms: List<TeacherClassroom> = listOf(TeacherClassroom(teacher, classroom), TeacherClassroom(teacher2, classroom2))
    //OutputPair(pair = Pair(1, 1, true, 1, time = null, discipline = Discipline(1, "go to the naxuy"), teacherClassrooms = teacherClassrooms))
}