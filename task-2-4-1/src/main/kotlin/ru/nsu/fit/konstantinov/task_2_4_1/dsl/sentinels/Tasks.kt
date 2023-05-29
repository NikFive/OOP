package ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.TaskBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Task

class Tasks : ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit) {
        add(TaskBuilder().apply(block).buildTask())
    }

}
