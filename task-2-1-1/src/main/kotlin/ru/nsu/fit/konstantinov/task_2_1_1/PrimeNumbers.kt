package ru.nsu.fit.konstantinov.task_2_1_1


interface PrimeNumbers {
    suspend fun isPrimeNumber(number: Int): Boolean
    fun containsPrimeNumbers(array: ArrayList<Int>): Boolean
}
