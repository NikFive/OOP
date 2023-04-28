package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers
import kotlin.math.sqrt

class ParallelStreamImpl {
    companion object : PrimeNumbers {
        private fun isPrimeNumber(number: Int): Boolean {
            var result = false
            for (i in 2..sqrt(number.toDouble()).toInt() + 1) {
                if (number % i == 0) {
                    result = true
                    break
                }
            }
            return result
        }

        override fun containsPrimeNumbers(array: ArrayList<Int>): Boolean =
            array.parallelStream().anyMatch { isPrimeNumber(it) }
    }
}
