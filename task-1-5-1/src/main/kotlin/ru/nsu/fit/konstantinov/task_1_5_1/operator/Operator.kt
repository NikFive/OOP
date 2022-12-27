package ru.nsu.fit.konstantinov.task_1_5_1.operator

import ru.nsu.fit.konstantinov.task_1_5_1.calculator.Calculator
import ru.nsu.fit.konstantinov.task_1_5_1.operators.MyNumber

interface Operator {
    var token: String
    fun calculate(): MyNumber
    fun provideArguments(arity: Int): MutableList<MyNumber> = mutableListOf<MyNumber>().apply {
        repeat (arity) {
            this.add(Calculator.valueStack.removeLast())
        }
    }
}
