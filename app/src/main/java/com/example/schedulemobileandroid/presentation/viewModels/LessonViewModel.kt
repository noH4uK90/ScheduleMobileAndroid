//package com.example.schedulemobileandroid.presentation.viewModels
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.schedulemobileandroid.domain.util.getShortFullName
//import com.example.schedulemobileandroid.models.Lesson
//import com.example.schedulemobileandroid.models.LessonChange
//import com.example.schedulemobileandroid.models.TeacherClassroom
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedFactory
//import dagger.assisted.AssistedInject
//import dagger.hilt.android.lifecycle.HiltViewModel
//
//@HiltViewModel
//class LessonViewModel @AssistedInject constructor(
//    @Assisted private val lesson: Lesson,
//    @Assisted private val date: String
//) : ViewModel() {
//
//    @AssistedFactory
//    interface Factory {
//        fun create(lesson: Lesson, date: String)
//    }
//
//    val timeStart = MutableLiveData<String>()
//    val timeEnd = MutableLiveData<String>()
//    val discipline = MutableLiveData<String>()
//    val firstTeacher = MutableLiveData<String>()
//    val secondTeacher = MutableLiveData<String>()
//    val firstCabinet = MutableLiveData<String>()
//    val secondCabinet = MutableLiveData<String>()
//
//    val isChanged = MutableLiveData<Boolean>()
//    val isOneTeacher = MutableLiveData<Boolean>()
//    val isOneCabinet = MutableLiveData<Boolean>()
//
//    private var lessonChange: LessonChange? = null
//
//    init {
//        updateLessonDetails()
//    }
//
//    private fun updateLessonDetails() {
//        timeStart.value = lesson.timeStart?.replace("AM", "")?.replace("PM", "") ?: ""
//        timeEnd.value = lesson.timeEnd?.replace("AM", "")?.replace("PM", "") ?: ""
//        discipline.value = lesson.discipline?.name?.name ?: ""
//
//        updateTeachersAndCabinets(lesson.lessonTeacherClassrooms)
//
//        lessonChange = lesson.lessonChanges.firstOrNull { it.date == date }
//        if (lessonChange != null) {
//            isChanged.value = true
//
//            timeStart.value = lessonChange?.timeStart?.replace("AM", "")?.replace("PM", "")
//            timeEnd.value = lessonChange?.timeEnd?.replace("AM", "")?.replace("PM", "")
//            discipline.value = lessonChange?.discipline?.name?.name
//
//            updateTeachersAndCabinets(lessonChange?.lessonTeacherClassrooms ?: emptyList())
//        }
//    }
//
//    private fun updateTeachersAndCabinets(teacherClassrooms: List<TeacherClassroom>) {
//        isOneTeacher.value = teacherClassrooms.size <= 1
//        isOneCabinet.value = teacherClassrooms.size <= 1
//
//        if (teacherClassrooms.isNotEmpty()) {
//            firstTeacher.value = getShortFullName(teacherClassrooms.first().teacher)
//            firstCabinet.value = teacherClassrooms.first().classroom.cabinet
//        }
//
//        if (teacherClassrooms.size > 1) {
//            secondTeacher.value = getShortFullName(teacherClassrooms.last().teacher)
//            secondCabinet.value = teacherClassrooms.last().classroom.cabinet
//        }
//    }
//}
//
