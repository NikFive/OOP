package ru.nsu.fit.konstantinov.task_1_5_1

import java.util.*
import kotlin.math.*

class MyCalc {
    companion object {
        private val binaryOperations: HashMap<String, (first: Double, second: Double) -> Double> =
            hashMapOf(Pair("+") { first: Double, second: Double -> first + second },
                Pair("-") { first: Double, second: Double -> first - second },
                Pair("/") { first: Double, second: Double -> first / second },
                Pair("*") { first: Double, second: Double -> first * second },
                Pair("pow") { first: Double, second: Double -> first.pow(second) },
                Pair("log") { first: Double, second: Double -> log(first, second) })
        private val unaryOperations: HashMap<String, (first: Double) -> Double> =
            hashMapOf(Pair("sin") { first: Double -> sin(first) },
                Pair("cos") { first: Double -> cos(first) },
                Pair("sqrt") { first: Double -> sqrt(first) })
        fun calculate(input: String): Double {
            var result = 0.0
            val valueStack = Stack<Double>()
            val reversedInput = input.split(" ").reversed()
            for (i in reversedInput) {
                if (i.toDoubleOrNull() != null) {
                    valueStack.add(i.toDouble())
                } else {
                    result = if (binaryOperations.containsKey(i)) {
                        binaryOperations[i]?.let { it(valueStack.pop(), valueStack.pop()) }!!
                    } else if (unaryOperations.containsKey(i)) {
                        unaryOperations[i]?.let { it(valueStack.pop()) }!!
                    } else {
                        throw Exception("Unknown operator")
                    }
                    valueStack.add(result)
                }
            }
            return result
        }
    }
}
