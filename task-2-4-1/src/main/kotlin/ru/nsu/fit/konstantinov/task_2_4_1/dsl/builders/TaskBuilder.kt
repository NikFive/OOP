package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Task
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.TaskList
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels.Tasks

class TaskBuilder {

    var tasks = mutableListOf<Task>()
    fun tasks(block: Tasks.() -> Unit) {
        tasks.addAll(Tasks().apply(block))
    }

    var taskId = ""
    var taskName = ""
    var score = -1
    fun buildTask(): Task {
        if (taskId == "") {
            throw IllegalArgumentException("TaskID is required")
        }
        if (taskName == "") {
            throw IllegalArgumentException("Task name is required")
        }
        if (score == -1) {
            throw IllegalArgumentException("Score is required")
        }
        return Task(taskId, taskName, score)
    }

    fun build(): TaskList {
        return TaskList(tasks)
    }

}
