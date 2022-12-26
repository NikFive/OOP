package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sinh

class Cos : Operator {
    override var token = "cos"

    override fun calculate(): MyNumber {
        val first = provideArguments(1)[0]
        return MyNumber(
            sinh(first.imaginary) * cos(first.real), -1 * sinh(first.imaginary) * sin(first.real)
        )
    }
}
