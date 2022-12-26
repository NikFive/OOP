package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import kotlin.math.pow

class Divide : Operator {
    override var token = "/"
    override fun calculate(): MyNumber {
        val complexOperands: List<MyNumber> = provideArguments(2)
        val first = complexOperands[0]
        val second = complexOperands[1]
        return MyNumber(
            (first.real * second.real + first.imaginary * second.imaginary) / second.complexABS().pow(2.0),
            (first.imaginary * second.real - first.real * second.imaginary) / second.complexABS().pow(2.0)
        )
    }
}
