package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers
import kotlin.math.sqrt

class Coroutines {
    companion object : PrimeNumbers {
        override suspend fun isPrimeNumber(number: Int): Boolean {
            var result = false
            GlobalScope.async {
                for (i in 2..sqrt(number.toDouble()).toInt() + 1) {
                    if (number % i == 0) {
                        result = true
                        break
                    }
                }
            }.await()
            return result
        }

        override fun containsPrimeNumbers(array: ArrayList<Int>): Boolean {
            var result = false
            runBlocking {
                for (number in array) {
                    result = isPrimeNumber(number)
                    if (result) {
                        break
                    }
                }
            }
            return result
        }
    }
}
