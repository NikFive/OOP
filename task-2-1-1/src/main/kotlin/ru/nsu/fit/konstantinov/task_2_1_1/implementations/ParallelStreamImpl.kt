package ru.nsu.fit.konstantinov.task_2_1_1.implementations

import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers

class ParallelStreamImpl {
    companion object : PrimeNumbers {
        override fun containsPrimeNumbers(array: ArrayList<Int>, numberOfFlows: Int): Boolean =
            array.parallelStream().anyMatch { isPrimeNumber(it) }
    }
}
