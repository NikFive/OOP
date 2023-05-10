package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class SerialImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>, numberOfFlows: Int): Boolean {
            var result = false
            for (number in array) {
                result = isPrimeNumber(number)
                if (result) {
                    break
                }
            }
            return result
        }
    }
}
