package com.example.schedulemobile.data.mappers

import com.example.schedulemobile.data.network.networkClassroom.NetworkClassroom
import com.example.schedulemobile.data.network.networkClassroom.NetworkClassroomList
import com.example.schedulemobile.data.network.networkClassroomType.NetworkClassroomType
import com.example.schedulemobile.data.network.networkClassroomType.NetworkClassroomTypeList
import com.example.schedulemobile.data.network.networkCurrentTimetable.NetworkCurrentTimetableList
import com.example.schedulemobile.data.network.networkDay.NetworkDay
import com.example.schedulemobile.data.network.networkDay.NetworkDayList
import com.example.schedulemobile.data.network.networkDiscipline.NetworkDiscipline
import com.example.schedulemobile.data.network.networkDiscipline.NetworkDisciplineList
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.data.network.networkGroup.NetworkGroupList
import com.example.schedulemobile.data.network.networkGroupedTimetable.NetworkGroupedTimetable
import com.example.schedulemobile.data.network.networkPair.NetworkPair
import com.example.schedulemobile.data.network.networkPair.NetworkPairList
import com.example.schedulemobile.data.network.networkPairTime.NetworkPairTime
import com.example.schedulemobile.data.network.networkPairTime.NetworkPairTimeList
import com.example.schedulemobile.data.network.networkPairTimeType.NetworkPairTimeType
import com.example.schedulemobile.data.network.networkPairTimeType.NetworkPairTimeTypeList
import com.example.schedulemobile.data.network.networkTeacher.NetworkTeacher
import com.example.schedulemobile.data.network.networkTeacher.NetworkTeacherList
import com.example.schedulemobile.data.network.networkTeacherClassroom.NetworkTeacherClassroom
import com.example.schedulemobile.data.network.networkTeacherClassroom.NetworkTeacherClassroomList
import com.example.schedulemobile.data.network.networkTimetable.NetworkTimetable
import com.example.schedulemobile.data.network.networkTimetable.NetworkTimetableList
import com.example.schedulemobile.data.network.networkTimetableType.NetworkTimetableType
import com.example.schedulemobile.data.network.networkTimetableType.NetworkTimetableTypeList
import com.example.schedulemobile.data.network.networkWeekType.NetworkWeekType
import com.example.schedulemobile.data.network.networkWeekType.NetworkWeekTypeList
import com.example.schedulemobile.domain.models.classroom.Classroom
import com.example.schedulemobile.domain.models.classroom.ClassroomList
import com.example.schedulemobile.domain.models.classroomType.ClassroomType
import com.example.schedulemobile.domain.models.classroomType.ClassroomTypeList
import com.example.schedulemobile.domain.models.currentTimetable.CurrentTimetable
import com.example.schedulemobile.domain.models.currentTimetable.CurrentTimetableList
import com.example.schedulemobile.domain.models.day.Day
import com.example.schedulemobile.domain.models.day.DayList
import com.example.schedulemobile.domain.models.discipline.Discipline
import com.example.schedulemobile.domain.models.discipline.DisciplineList
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.models.groupedTimetable.GroupedTimetable
import com.example.schedulemobile.domain.models.pair.Pair
import com.example.schedulemobile.domain.models.pair.PairList
import com.example.schedulemobile.domain.models.pairTime.PairTime
import com.example.schedulemobile.domain.models.pairTime.PairTimeList
import com.example.schedulemobile.domain.models.pairTimeType.PairTimeType
import com.example.schedulemobile.domain.models.pairTimeType.PairTimeTypeList
import com.example.schedulemobile.domain.models.teacher.Teacher
import com.example.schedulemobile.domain.models.teacher.TeacherList
import com.example.schedulemobile.domain.models.teacherClassroom.TeacherClassroom
import com.example.schedulemobile.domain.models.teacherClassroom.TeacherClassroomList
import com.example.schedulemobile.domain.models.timetable.Timetable
import com.example.schedulemobile.domain.models.timetable.TimetableList
import com.example.schedulemobile.domain.models.timetableType.TimetableType
import com.example.schedulemobile.domain.models.timetableType.TimetableTypeList
import com.example.schedulemobile.domain.models.weekType.WeekType
import com.example.schedulemobile.domain.models.weekType.WeekTypeList


fun NetworkCurrentTimetableList.toCurrentTimetableList(): CurrentTimetableList {
    return CurrentTimetableList(
        items = items.map {
            CurrentTimetable(
                it.group.toGroup(),
                it.days.map(NetworkGroupedTimetable<NetworkDay>::toGroupedTimetable)
            )
        }
    )
}

fun NetworkClassroomList.toClassroomList(): ClassroomList {
    return ClassroomList(
        items = items.map(NetworkClassroom::toClassroom)
    )
}

fun NetworkClassroomTypeList.toClassroomTypeList(): ClassroomTypeList {
    return ClassroomTypeList(
        items = items.map(NetworkClassroomType::toClassroomType)
    )
}

fun NetworkDayList.toDayList(): DayList {
    return DayList(
        items = items.map(NetworkDay::toDay)
    )
}

fun NetworkDisciplineList.toDisciplineList(): DisciplineList {
    return DisciplineList(
        items = items.map(NetworkDiscipline::toDiscipline)
    )
}

fun NetworkGroupList.toGroupList(): GroupList {
    return GroupList(
        items = items.map(NetworkGroup::toGroup)
    )
}

fun NetworkPairList.toPairList(): PairList {
    return PairList(
        items = items.map(NetworkPair::toPair)
    )
}

fun NetworkPairTimeList.toPairTimeList(): PairTimeList {
    return PairTimeList(
        items = items.map(NetworkPairTime::toPairTime)
    )
}

fun NetworkPairTimeTypeList.toPairTimeTypeList(): PairTimeTypeList {
    return PairTimeTypeList(
        items = items.map(NetworkPairTimeType::toPairTimeType)
    )
}

fun NetworkTeacherList.toTeacherList(): TeacherList {
    return TeacherList(
        items = items.map(NetworkTeacher::toTeacher)
    )
}

fun NetworkTeacherClassroomList.toTeacherClassroomList(): TeacherClassroomList {
    return TeacherClassroomList(
        items = items.map(NetworkTeacherClassroom::toTeacherClassroom)
    )
}

fun NetworkTimetableList.toTimetableList(): TimetableList {
    return TimetableList(
        items = items.map(NetworkTimetable::toTimetable)
    )
}

fun NetworkTimetableTypeList.toTimetableTypeList(): TimetableTypeList {
    return TimetableTypeList(
        items = items.map(NetworkTimetableType::toTimetableType)
    )
}

fun NetworkWeekTypeList.toWeekTypeList(): WeekTypeList {
    return WeekTypeList(
        items = items.map(NetworkWeekType::toWeekType)
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
        disciplines = disciplines.map(NetworkDiscipline::toDiscipline)
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
        items = items.map(NetworkTimetable::toTimetable)
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
        teacherClassrooms = teacherClassrooms.map(NetworkTeacherClassroom::toTeacherClassroom)
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
        groups = groups.map(NetworkGroup::toGroup),
        disciplines = disciplines.map(NetworkDiscipline::toDiscipline)
    )
}

fun NetworkClassroom.toClassroom(): Classroom {
    return Classroom(
        id = id,
        cabinet = cabinet,
        types = types.map(NetworkClassroomType::toClassroomType)
    )
}

fun NetworkPairTime.toPairTime(): PairTime {
    return PairTime(
        id = id,
        start = start,
        end = end,
        pairNumber = pairNumber,
        type = type?.toPairTimeType()
    )
}

fun NetworkTimetable.toTimetable(): Timetable {
    return Timetable(
        id = id,
        day = day.toDay(),
        group = group.toGroup(),
        type = type.toTimetableType(),
        pairs = pairs.map(NetworkPair::toPair),
        weekType = weekType.toWeekType()
    )
}