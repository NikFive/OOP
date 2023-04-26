package ru.nsu.fit.konstantinov.task_2_1_1

import org.openjdk.jmh.annotations.*
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.Coroutines
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.ParallelStream
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.Serial
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
        assertEquals(Serial.containsPrimeNumbers(testArray1), true)
        assertEquals(Serial.containsPrimeNumbers(testArray2), false)
    }

    @Test
    @Benchmark
    fun testCoroutines() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = arrayListOf(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)
        assertEquals(Coroutines.containsPrimeNumbers(testArray1), true)
        assertEquals(Coroutines.containsPrimeNumbers(testArray2), false)
    }

    @Test
    @Benchmark
    fun testParallelStream() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = arrayListOf(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)
        assertEquals(ParallelStream.containsPrimeNumbers(testArray1), true)
        assertEquals(ParallelStream.containsPrimeNumbers(testArray2), false)
    }
}
