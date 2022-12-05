package ru.nsu.fit.konstantinov.task_1_5_1


class MyCalc {
    companion object {
        fun calculate(input: String): Double {
            val reversedArray = input.split(" ").reversed().toTypedArray()
            var result = 0.0
            var operationIndex = getOperationStart(reversedArray)
            var valueIndex = 0
            while (operationIndex < reversedArray.size) {
                if (valueIndex == 0) {
                    result = CalcOperation.operation(
                        reversedArray[operationIndex], reversedArray[0].toDouble(), reversedArray[1].toDouble()
                    )
                    valueIndex = 2
                } else {
                    result = CalcOperation.operation(
                        reversedArray[operationIndex], result, reversedArray[valueIndex].toDouble()
                    )
                    valueIndex++
                }
                operationIndex++
            }
            return result
        }

        private fun getOperationStart(arrayToCheck: Array<String>): Int {
            var result: Int = arrayToCheck.size
            for (i in arrayToCheck.indices) {
                if (arrayToCheck[i].toDoubleOrNull() == null) {
                    result = i
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
