package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.GivenTask
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GivenTaskBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    var taskId = ""
    private var deadLineDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var deadLine: String = ""
        set(value) {
            deadLineDate = LocalDate.parse(value, dateTimeFormatter)
        }

    fun build(): GivenTask {
        if (taskId == "") {
            throw IllegalArgumentException("TaskID is required")
        }
        if (deadLineDate == LocalDate.parse("01-01-0001", dateTimeFormatter)) {
            throw IllegalArgumentException("Deadline is required")
        }
        return GivenTask(taskId, deadLineDate)
    }

}
