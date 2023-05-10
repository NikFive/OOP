package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class CoroutinesImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>, numberOfFlows: Int): Boolean {
            var result = false
            CoroutineScope(Dispatchers.Default).launch {
                for (number in array) {
                    launch(start = CoroutineStart.LAZY) {
                        result = isPrimeNumber(number)
                    }.join()
                    if (result) {
                        break
                    }
                }
            }
            return result
        }
    }
}
