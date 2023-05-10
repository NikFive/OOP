package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class ThreadsImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>, numberOfFlows: Int): Boolean {
            print(numberOfFlows)
            val customThread = CustomThread(array)
            val threads: MutableList<Thread> = ArrayList()
            for (i in 0 until numberOfFlows) {
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

    private class CustomThread(array: ArrayList<Int>) : Runnable {
        private val iterator: Iterator<Int> = array.iterator()
        var result = false

        override fun run() {
            do {
                val number = next() ?: break
                result = isPrimeNumber(number)
            } while (true)
        }

        private fun next(): Int? {
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
