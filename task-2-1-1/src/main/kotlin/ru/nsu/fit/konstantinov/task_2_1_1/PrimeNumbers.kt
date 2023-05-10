package ru.nsu.fit.konstantinov.task_2_1_1

import kotlin.math.sqrt


interface PrimeNumbers {
    fun containsPrimeNumbers(
        array: ArrayList<Int>,
        numberOfFlows: Int = Runtime.getRuntime().availableProcessors()
    ): Boolean

    fun isPrimeNumber(number: Int): Boolean {
        var result = false
        for (i in 2..sqrt(number.toDouble()).toInt() + 1) {
            if (number % i == 0) {
                result = true
                break
            }
        }
        return result
    }
}
