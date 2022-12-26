package ru.nsu.fit.konstantinov.task_1_5_1.my_operators

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import ru.nsu.fit.konstantinov.task_1_5_1.operators.MyNumber

class TernaryPlus : Operator {
    override var token = "!+"
    override fun calculate(): MyNumber {
        val complexOperands: List<MyNumber> = provideArguments(3)
        return MyNumber(
            complexOperands[0].real + complexOperands[1].real + complexOperands[2].real,
            complexOperands[0].imaginary + complexOperands[1].imaginary + complexOperands[2].real
        )
    }
}
