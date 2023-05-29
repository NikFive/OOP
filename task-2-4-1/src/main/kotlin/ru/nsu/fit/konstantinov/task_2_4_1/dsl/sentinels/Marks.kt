package ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.MarkBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Mark

class Marks : ArrayList<Mark>() {
    fun mark(block: MarkBuilder.() -> Unit) {
        add(MarkBuilder().apply(block).build())
    }

}
