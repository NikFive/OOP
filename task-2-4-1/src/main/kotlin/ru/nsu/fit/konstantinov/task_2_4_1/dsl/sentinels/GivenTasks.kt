package ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.GivenTaskBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.GivenTask

class GivenTasks : ArrayList<GivenTask>() {
    fun givenTask(block: GivenTaskBuilder.() -> Unit) {
        add(GivenTaskBuilder().apply(block).build())
    }
}
