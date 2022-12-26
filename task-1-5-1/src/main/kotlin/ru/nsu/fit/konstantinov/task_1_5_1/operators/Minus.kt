package ru.nsu.fit.konstantinov.task_1_5_1.operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator

class Minus : Operator {
    override var token = "-"
    override fun calculate(): MyNumber {
        val complexOperands: List<MyNumber> = provideArguments(2)
        return MyNumber(
            complexOperands[0].real - complexOperands[1].real,
            complexOperands[0].imaginary - complexOperands[1].imaginary
        )
    }
}
