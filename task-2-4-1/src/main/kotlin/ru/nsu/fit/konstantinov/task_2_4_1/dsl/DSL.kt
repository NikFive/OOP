package ru.nsu.fit.konstantinov.task_2_4_1.dsl

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.StudentBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.TaskBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Student
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.TaskList


class DSL {
    fun student(block: StudentBuilder.() -> Unit): Student = StudentBuilder().apply(block).build()
    fun taskList(block: TaskBuilder.() -> Unit): TaskList = TaskBuilder().apply(block).build()
}
