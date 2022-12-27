package ru.nsu.fit.konstantinov.task_1_5_1.calculator

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import ru.nsu.fit.konstantinov.task_1_5_1.operator.OperatorFactory
import ru.nsu.fit.konstantinov.task_1_5_1.operators.MyNumber

class Calculator {
    companion object {
        internal val valueStack: ArrayDeque<MyNumber> = ArrayDeque()
        private var operatorFactory: OperatorFactory = OperatorFactory()
        fun calculate(expression: String): MyNumber = valueStack.apply {
            expression.split(" ").toTypedArray().reversedArray().forEach { i ->
                valueStack.addLast(operatorFactory.callOperator(i).calculate())
            }.also { check(valueStack.size != 0) { "The expression is incorrect." } }
        }.removeFirst()
    }

    fun calculate(expression: String) = Calculator.calculate(expression)

    fun addOuterOperators(vararg operators: Operator) = operatorFactory.addOuterOperators(operators.toList())
}
