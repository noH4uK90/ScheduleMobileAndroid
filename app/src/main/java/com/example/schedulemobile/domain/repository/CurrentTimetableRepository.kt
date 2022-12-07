package com.example.schedulemobile.domain.repository

import com.example.schedulemobile.domain.models.currentTimetable.CurrentTimetableList
import com.example.schedulemobile.domain.util.Resource

interface CurrentTimetableRepository {
    suspend fun getCurrentTimetableList(groupId: Int): Resource<CurrentTimetableList>
}