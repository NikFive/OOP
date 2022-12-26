package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import java.lang.Math.toDegrees

class Deg : Operator {
    override var token = "deg"

    override fun calculate(): MyNumber {
        val first = provideArguments(1)[0]
        return MyNumber(toDegrees(first.real), 0.0)
    }
}
