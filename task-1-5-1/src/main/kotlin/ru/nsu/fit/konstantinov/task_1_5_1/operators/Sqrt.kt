package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Sqrt : Operator {
    override var token = "sqrt"
    override fun calculate(): MyNumber {
        val first = provideArguments(1)[0]
        val r = sqrt(first.complexABS())
        val theta: Double = first.arg() / 2
        return MyNumber(r * cos(theta), r * sin(theta))
    }
}
