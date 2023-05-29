package ru.nsu.fit.konstantinov.task_2_4_1.dsl.sentinels

import ru.nsu.fit.konstantinov.task_2_4_1.dsl.builders.LessonsBuilder
import ru.nsu.fit.konstantinov.task_2_4_1.dsl.models.Lesson

class Lessons : ArrayList<Lesson>() {
    fun lesson(block: LessonsBuilder.() -> Unit) {
        add(LessonsBuilder().apply(block).build())
    }
}
