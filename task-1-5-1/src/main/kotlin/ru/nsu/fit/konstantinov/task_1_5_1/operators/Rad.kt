package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import java.lang.Math.toRadians

class Rad : Operator {
    override var token = "rad"

    override fun calculate(): MyNumber {
        val first = provideArguments(1)[0]
        return MyNumber(toRadians(first.real), 0.0)
    }
}
