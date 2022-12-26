package ru.nsu.fit.konstantinov.task_1_5_1.operator

import ru.nsu.fit.konstantinov.task_1_5_1.operators.*

class OperatorFactory {
    private val operatorList: MutableList<Operator> =
        arrayListOf(Plus(), Minus(), Multiply(), Divide(), Sqrt(), Pow(), Sin(), Deg(), Cos(), Rad(), Log())

    fun addOuterOperators(outerOperationsList: List<Operator>) = operatorList.addAll(outerOperationsList)

    fun callOperator(token: String): Operator {
        for (operator in operatorList) {
            if (operator.token == token) {
                return operator
            }
        }
        return MyNumber(token)
    }
}
