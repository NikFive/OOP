package ru.nsu.fit.konstantinov.task_1_5_1

import java.util.*

class MyCalc {
    companion object {
        fun calculate(input: String): Double {
            var result = 0.0
            val valueStack = Stack<Double>()
            val reversedInput = input.split(" ").reversed()
            for (i in reversedInput) {
                if (i.toDoubleOrNull() != null) {
                    valueStack.add(i.toDouble())
                } else {
                    result = CalcOperation.operation(i, valueStack.pop(), valueStack.pop())
                    valueStack.add(result)
                }
            }
            return result
        }
    }
}

interface CalcOperation {
    companion object {
        fun operation(operationName: String, first: Double, second: Double) = when (operationName) {
            "+" -> PlusClass(first, second).operation()
            "-" -> MinusClass(first, second).operation()
            "*" -> MultiplyClass(first, second).operation()
            "/" -> DivideClass(first, second).operation()
            else -> throw Exception("I don't know how to deal with $operationName.")
        }
    }

    class PlusClass(private val first: Double, private val second: Double) : CalcOperation {
        fun operation() = first + second
    }

    class MinusClass(private val first: Double, private val second: Double) : CalcOperation {
        fun operation() = first - second
    }

    class MultiplyClass(private val first: Double, private val second: Double) : CalcOperation {
        fun operation() = first * second
    }

    class DivideClass(private val first: Double, private val second: Double) : CalcOperation {
        fun operation() = first / second
    }
}
