package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class CoroutinesImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>): Boolean {
            var result = false
            runBlocking {
                for (number in array) {
                    launch { result = isPrimeNumber(number) }.join()
                    if (result) {
                        break
                    }
                }
            }
            return result
        }
    }
}
