package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.pow

class Pow : Operator {
    override var token = "^"
    override fun calculate(): MyNumber {
        val complexOperands: List<MyNumber> = provideArguments(2)
        return MyNumber(complexOperands[0].real.pow(complexOperands[1].real), 0.0)
    }
}
