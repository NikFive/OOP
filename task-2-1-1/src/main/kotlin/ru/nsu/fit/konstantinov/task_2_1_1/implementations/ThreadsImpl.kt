package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers
import kotlin.math.sqrt

class ThreadsImpl {
    companion object : PrimeNumbers {
        private var numberOfThreads = 5

        override fun containsPrimeNumbers(array: ArrayList<Int>): Boolean {
            val customThread = CustomThread(array)
            val threads: MutableList<Thread> = ArrayList()
            for (i in 0 until numberOfThreads) {
                threads.add(Thread(customThread))
                threads[i].start()
            }
            for (thread in threads) {
                try {
                    thread.join()
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                }
            }
            return customThread.result
        }
    }

    private class CustomThread(array: ArrayList<Int>): Runnable {
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

        private val iterator: Iterator<Int> = array.iterator()
        var result = false

        override fun run() {
            var number: Int?
            while (next.also { number = it } != null) {
                if (number?.let { isPrimeNumber(it) } == true) {
                    result = true
                }
            }
        }

        private val next: Int?
            get() {
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
    }
}
