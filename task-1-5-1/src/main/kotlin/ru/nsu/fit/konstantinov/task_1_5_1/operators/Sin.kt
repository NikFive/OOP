package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.sin
import kotlin.math.sinh

class Sin : Operator {
    override var token = "sin"

    override fun calculate(): MyNumber {
        val fst = provideArguments(1)[0]
        return MyNumber(
            cosh(fst.imaginary) * sin(fst.real), sinh(fst.imaginary) * cos(fst.real)
        )
    }
}
