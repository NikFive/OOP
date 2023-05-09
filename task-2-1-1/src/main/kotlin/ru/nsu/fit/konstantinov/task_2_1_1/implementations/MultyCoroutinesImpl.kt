package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class MultyCoroutinesImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>): Boolean {
            var result = false
            runBlocking {
                for (number in array) {
                    GlobalScope.async { result = isPrimeNumber(number) }.await()
                    if (result) {
                        break
                    }
                }
            }
            return result
        }
    }
}
