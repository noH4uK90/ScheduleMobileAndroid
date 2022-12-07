package com.example.schedulemobile

import com.airbnb.epoxy.TypedEpoxyController
import com.example.schedulemobile.domain.models.timetable.Timetable

class TimetableEpoxyController: TypedEpoxyController<List<Timetable>>() {

    override fun buildModels(data: List<Timetable>?) {
    }
}