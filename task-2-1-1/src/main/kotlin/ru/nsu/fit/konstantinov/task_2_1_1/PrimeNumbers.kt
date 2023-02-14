package ru.nsu.fit.konstantinov.task_2_1_1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.sqrt


class PrimeNumbers {
    companion object {
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

        private fun isPrimeNumberSuspend(number: Int): Boolean {
            var result = false
            for (i in 2..sqrt(number.toDouble()).toInt() + 1) {
                if (number % i == 0) {
                    result = true
                    break
                }
            }
            return result
        }

        fun containsPrimeNumbersSerial(array: ArrayList<Int>): Boolean {
            var result = false
            for (number in array) {
                result = isPrimeNumber(number)
                if (result) {
                    break
                }
            }
            return result
        }


        fun containsPrimeNumbersParallel(array: ArrayList<Int>): Boolean {
            var result = false
            runBlocking {
                launch {
                    for (number in array) {
                        result = isPrimeNumber(number)
                        if (result) {
                            break
                        }
                    }
                }
            }
            return result
        }
    }
}
