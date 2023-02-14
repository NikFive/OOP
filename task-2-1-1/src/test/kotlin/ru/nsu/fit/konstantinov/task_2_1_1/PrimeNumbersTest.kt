package ru.nsu.fit.konstantinov.task_2_1_1

import org.openjdk.jmh.annotations.*
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers.Companion.containsPrimeNumbersParallel
import ru.nsu.fit.konstantinov.task_2_1_1.PrimeNumbers.Companion.containsPrimeNumbersSerial
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)

class PrimeNumbersTest {

    @Test
    @Benchmark
    fun testSerial() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = arrayListOf(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)
        assertEquals(containsPrimeNumbersSerial(testArray1), true)
        assertEquals(containsPrimeNumbersSerial(testArray2), false)
    }

    @Test
    @Benchmark
    fun testParallel() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = arrayListOf(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)
        assertEquals(containsPrimeNumbersParallel(testArray1), true)
        assertEquals(containsPrimeNumbersParallel(testArray2), false)
    }
}
