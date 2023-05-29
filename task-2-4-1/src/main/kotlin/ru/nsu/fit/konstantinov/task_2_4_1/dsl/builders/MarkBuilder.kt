package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Mark
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarkBuilder {
    var name = 0
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    private var markDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var date: String = ""
        set(value) {
            markDate = LocalDate.parse(value, dateTimeFormatter)
        }


    fun build(): Mark {
        if (name == 0) {
            throw IllegalArgumentException("Mark is required")
        }
        if (markDate == LocalDate.parse("01-01-0001", dateTimeFormatter)) {
            throw IllegalArgumentException("Deadline is required")
        }
        return Mark(name, markDate)
    }
}
