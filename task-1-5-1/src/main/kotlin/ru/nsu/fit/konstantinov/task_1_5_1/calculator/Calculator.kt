package ru.nsu.fit.konstantinov.task_1_5_1.calculator

import ru.nsu.fit.konstantinov.task_1_5_1.operator.Operator
import ru.nsu.fit.konstantinov.task_1_5_1.operator.OperatorFactory
import ru.nsu.fit.konstantinov.task_1_5_1.operators.MyNumber
import java.util.*
import kotlin.collections.ArrayDeque


class Calculator {
    companion object {
        var valueStack: ArrayDeque<MyNumber> = ArrayDeque()
        private var operatorFactory: OperatorFactory = OperatorFactory()
        fun calculate(expression: String): MyNumber {
            for (i in expression.split(" ").toTypedArray().reversedArray()) {
                valueStack.addLast(operatorFactory.callOperator(i).calculate())
            }
            check(valueStack.size != 0) { "The expression is incorrect." }
            return valueStack.removeFirst()
        }
    }

    fun calculate(expression: String): MyNumber {
        for (i in expression.split(" ").toTypedArray().reversedArray()) {
            valueStack.addLast(operatorFactory.callOperator(i).calculate())
        }
        check(valueStack.size != 0) { "The expression is incorrect." }
        return valueStack.removeFirst()
    }

    fun addOuterOperators(vararg operators: Operator) =
        operatorFactory.addOuterOperators(Arrays.stream(operators).toList())
}
