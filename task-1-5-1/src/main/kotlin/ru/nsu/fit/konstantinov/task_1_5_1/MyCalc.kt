package ru.nsu.fit.konstantinov.task_1_5_1


class MyCalc {
    interface CalcOperation {
        companion object {
            fun operation(operationName: String, first: Double, second: Double) = when (operationName) {
                "+" -> PlusClass(first, second).operation()
                "-" -> MinusClass(first, second).operation()
                else -> throw Exception("I don't know how to deal with $operationName.")
            }
        }

        class PlusClass(private val first: Double, private val second: Double) : CalcOperation {
            fun operation() = first + second
        }

        class MinusClass(private val first: Double, private val second: Double) : CalcOperation {
            fun operation() = first - second
        }
    }

}
