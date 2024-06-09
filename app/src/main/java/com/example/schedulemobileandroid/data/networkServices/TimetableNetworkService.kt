package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.TimetableEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.Lesson
import com.example.schedulemobileandroid.models.PagedList
import com.example.schedulemobileandroid.models.Timetable
import okhttp3.internal.http.toHttpDateString
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

interface TimetableApiService {
    suspend fun getGroupTimetable(groupId: Int, date: LocalDate): Resource<List<Timetable>>
    suspend fun getTeacherTimetable(teacherFullNameId: Int, date: LocalDate): Resource<List<Lesson>>
}

class TimetableNetworkService @Inject constructor(
    private val endpoints: TimetableEndpoints
): TimetableApiService {
    override suspend fun getGroupTimetable(groupId: Int, date: LocalDate): Resource<List<Timetable>> {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            Resource.Success(
                data = endpoints.getGroupTimetable(groupId, date.format(formatter))
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getTeacherTimetable(
        teacherFullNameId: Int,
        date: LocalDate
    ): Resource<List<Lesson>> {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            Resource.Success(
                data = endpoints.getTeacherTimetable(teacherFullNameId, date.format(formatter))
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

}