package com.example.schedulemobileandroid.presentation.views.scheduleViews

import DateWheelView
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.TimetableApiService
import com.example.schedulemobileandroid.models.Lesson
import com.example.schedulemobileandroid.presentation.customComponents.LessonView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@Composable
fun TeacherScheduleView(teacherId: Int) {
    val viewModel: TeacherScheduleViewModel = hiltViewModel()
    val currentDate by viewModel.currentDate.collectAsState()
    val lessons by viewModel.lessons.collectAsState()

    LaunchedEffect(teacherId) {
        viewModel.setTeacherId(teacherId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DateWheelView(currentDate) { date -> viewModel.updateCurrentDate(date) }
        Spacer(modifier = Modifier.height(16.dp))
        if (lessons.isNotEmpty()) {
            LazyColumn {
                items(lessons) { lesson ->
                    LessonView(lesson = lesson)
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Пар на сегодня нет",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@HiltViewModel
class TeacherScheduleViewModel @Inject constructor(
    private val timetableNetworkService: TimetableApiService
): ViewModel(), DefaultLifecycleObserver {
    private val _currentDate = MutableStateFlow(LocalDate.now())
    val currentDate: StateFlow<LocalDate> = _currentDate

    private val _lessons = MutableStateFlow<List<Lesson>>(emptyList())
    val lessons: StateFlow<List<Lesson>> = _lessons

    private var teacherId: Int = 0

    init {
        viewModelScope.launch {
            getLessons(_currentDate.value)
        }
    }

    fun setTeacherId(id: Int) {
        teacherId = id
        viewModelScope.launch {
            getLessons(_currentDate.value)
        }
    }

    fun updateCurrentDate(date: LocalDate) {
        _currentDate.value = date
        viewModelScope.launch {
            getLessons(date)
        }
    }

    private suspend fun getLessons(date: LocalDate) {
        val response = timetableNetworkService.getTeacherTimetable(teacherId, date)
        _lessons.value = response.data ?: emptyList()
    }
}