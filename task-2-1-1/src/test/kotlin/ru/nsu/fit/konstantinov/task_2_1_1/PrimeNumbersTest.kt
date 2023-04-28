package ru.nsu.fit.konstantinov.task_2_1_1

import org.openjdk.jmh.annotations.*
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.CoroutinesImpl
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.ParallelStreamImpl
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.SerialImpl
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.ThreadsImpl
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)

class PrimeNumbersTest {
    val testData = arrayListOf(
        6997901,
        6997927,
        6997937,
        6997967,
        6998009,
    ).apply {
        for (i in 0..200) {
            this.addAll(
                arrayListOf(
                    6997901,
                    6997927,
                    6997937,
                    6997967,
                    6998009,
                )
            )
        }
    }

    @Test
    @Benchmark
    fun testSerial() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = testData
        assertEquals(SerialImpl.containsPrimeNumbers(testArray1), true)
        assertEquals(SerialImpl.containsPrimeNumbers(testArray2), false)
    }

    @Test
    @Benchmark
    fun testCoroutines() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = testData
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testArray1), true)
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testArray2), false)
    }

    @Test
    @Benchmark
    fun testParallelStream() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = testData
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testArray1), true)
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testArray2), false)
    }

    @Test
    @Benchmark
    fun testThreads() {
        val testArray1 = arrayListOf(6, 8, 7, 13, 9, 4)
        val testArray2 = testData
        assertEquals(ThreadsImpl.containsPrimeNumbers(testArray1), true)
        assertEquals(ThreadsImpl.containsPrimeNumbers(testArray2), false)
    }
}
