package com.example.schedulemobileandroid.domain.util

import com.example.schedulemobileandroid.models.Teacher
import java.time.LocalDate

fun getShortFullName(teacher: Teacher?): String {
    return if (teacher == null) "" else "${teacher.surname} ${teacher.name[0]}. ${teacher.middleName}" +
            if (teacher.middleName?.get(0) != null) "${teacher.middleName[0]}" else ""
}

fun getCurrentWeekDayNumber(): Int {
    return LocalDate.now().dayOfWeek.value
}