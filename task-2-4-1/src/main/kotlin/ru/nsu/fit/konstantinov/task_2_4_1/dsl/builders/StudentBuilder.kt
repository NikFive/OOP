package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.*
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels.GivenTasks
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels.Lessons
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels.Marks

class StudentBuilder {
    var name = ""
    var nickName = ""
    var repoUrl = ""

    lateinit var group: Group
    fun group(block: GroupBuilder.() -> Unit) {
        group = GroupBuilder().apply(block).build()
    }

    var givenTasks = mutableListOf<GivenTask>()

    fun tasks(block: GivenTasks.() -> Unit) {
        givenTasks.addAll(GivenTasks().apply(block))
    }

    var lessons = ArrayList<Lesson>()
    fun lessons(block: Lessons.() -> Unit) {
        lessons.addAll(Lessons().apply(block))
    }

    var marks = mutableListOf<Mark>()
    fun marks(block: Marks.() -> Unit) {
        marks.addAll(Marks().apply(block))
    }

    fun build(): Student {
        if (nickName == "") {
            throw IllegalArgumentException("Nickname is required")
        } else if (name == "") {
            throw IllegalArgumentException("Name is required")
        } else if (repoUrl == "") {
            throw IllegalArgumentException("URL of repository is required")
        }

        return Student(nickName, name, repoUrl, group, givenTasks, lessons, marks)
    }
}