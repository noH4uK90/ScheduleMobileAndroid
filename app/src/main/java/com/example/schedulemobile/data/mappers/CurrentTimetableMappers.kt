package com.example.schedulemobile.data.mappers

import com.example.schedulemobile.data.network.*
import com.example.schedulemobile.data.network.networkCurrentTimetable.NetworkCurrentTimetableList
import com.example.schedulemobile.data.network.networkDiscipline.NetworkDiscipline
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.data.network.networkGroup.NetworkGroupList
import com.example.schedulemobile.data.network.networkGroupedTimetable.NetworkGroupedTimetable
import com.example.schedulemobile.data.network.networkTimetable.NetworkTimetable
import com.example.schedulemobile.domain.models.*
import com.example.schedulemobile.domain.models.currentTimetable.CurrentTimetable
import com.example.schedulemobile.domain.models.currentTimetable.CurrentTimetableList
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.models.groupedTimetable.GroupedTimetable
import com.example.schedulemobile.domain.models.timetable.Timetable


fun NetworkCurrentTimetableList.toCurrentTimetableList(): CurrentTimetableList {
    return CurrentTimetableList(
        items = items.map {
            CurrentTimetable(
                it.group.toGroup(),
                it.days.map { day -> day.toGroupedTimetable() })
        }
    )
}

fun NetworkGroupList.toGroupList(): GroupList {
    return GroupList(
        items = items.map {
            it.toGroup()
        }
    )
}

fun NetworkDay.toDay(): Day {
    return Day(
        id = id,
        name = name
    )
}

fun NetworkTimetableType.toTimetableType(): TimetableType {
    return TimetableType(
        id = id,
        name = name
    )
}

fun NetworkDiscipline.toDiscipline(): Discipline {
    return Discipline(id = id, name = name)
}

fun NetworkGroup.toGroup(): Group {
    return Group(
        id = id,
        name = name,
        course = course,
        disciplines = disciplines.map { it.toDiscipline() }
    )
}

fun NetworkWeekType.toWeekType(): WeekType {
    return WeekType(
        id = id,
        name = name
    )
}

fun NetworkClassroomType.toClassroomType(): ClassroomType {
    return ClassroomType(
        id = id,
        name = name
    )
}

fun NetworkPairTimeType.toPairTimeType(): PairTimeType {
    return PairTimeType(
        id = id,
        name = name
    )
}

fun NetworkGroupedTimetable<NetworkDay>.toGroupedTimetable(): GroupedTimetable<Day> {
    return GroupedTimetable<Day>(
        key = key.toDay(),
        items = items.map { it.toTimetable() }
    )
}

fun NetworkPair.toPair(): Pair {
    return Pair(
        id = id,
        number = number,
        changed = changed,
        timetableId = timetableId,
        time = time?.toPairTime(),
        discipline = discipline?.toDiscipline(),
        teacherClassrooms = teacherClassrooms.map { it.toTeacherClassroom() }
    )
}

fun NetworkTeacherClassroom.toTeacherClassroom(): TeacherClassroom {
    return TeacherClassroom(
        teacher = teacher.toTeacher(),
        classroom = classroom.toClassroom()
    )
}

fun NetworkTeacher.toTeacher(): Teacher {
    return Teacher(
        id = id,
        name = name,
        surname = surname,
        middleName = middleName,
        groups = groups.map { it.toGroup() },
        disciplines = disciplines.map { it.toDiscipline() }
    )
}

fun NetworkClassroom.toClassroom(): Classroom {
    return Classroom(
        id = id,
        cabinet = cabinet,
        types = types.map { it.toClassroomType() }
    )
}

fun NetworkPairTime.toPairTime(): PairTime {
    return PairTime(
        id = id,
        start = start,
        end = end,
        pairNumber = pairNumber,
        type = type.toPairTimeType()
    )
}

fun NetworkTimetable.toTimetable(): Timetable {
    return Timetable(
        id = id,
        day = day.toDay(),
        group = group.toGroup(),
        type = type.toTimetableType(),
        pairs = pairs.map { it.toPair() },
        weekType = weekType.toWeekType()
    )
}