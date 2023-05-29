package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Lesson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonsBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    private var lessonDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var date: String = ""
        set(value) {
            lessonDate = LocalDate.parse(value, dateTimeFormatter)
        }

    var attendance = false
    fun build(): Lesson {
        if (lessonDate == LocalDate.parse("01-01-0001", dateTimeFormatter)) {
            System.err.println("You didn't specify a date of the lesson. Set automatically $lessonDate")
        }
        return Lesson(lessonDate, attendance)
    }

}
