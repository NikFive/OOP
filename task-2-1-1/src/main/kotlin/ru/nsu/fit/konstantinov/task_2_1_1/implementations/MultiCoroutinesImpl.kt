package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import kotlinx.coroutines.*
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class MultiCoroutinesImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>, numberOfFlows: Int): Boolean {
            val iterator: Iterator<Int> = array.iterator()
            var result = false
            val coroutines: ArrayList<Job> = arrayListOf()

            fun next(): Int? {
                if (result) {
                    return null
                }
                synchronized(iterator) {
                    if (iterator.hasNext()) {
                        return iterator.next()
                    }
                }
                return null
            }

            CoroutineScope(Dispatchers.Default).launch {
                for (i in 0 until numberOfFlows) {
                    coroutines.add(launch(start = CoroutineStart.LAZY) {
                        do {
                            val numbercheck = next() ?: break
                            result = isPrimeNumber(numbercheck)
                        } while (true)
                    })

                    withContext(Dispatchers.IO) {
                        coroutines.joinAll()
                    }

                    if (result) {
                        break
                    }
                }
            }

            return result
        }
    }
}
