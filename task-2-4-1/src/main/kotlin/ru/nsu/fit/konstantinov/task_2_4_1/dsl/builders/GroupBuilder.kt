package ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Group

class GroupBuilder {
    var name = 0

    fun build(): Group {
        if (name == 0) {
            throw IllegalArgumentException("Number of group is required")
        }
        return Group(name)
    }

}
