package ru.nsu.fit.konstantinov.task_2_1_1

import org.openjdk.jmh.annotations.*
import ru.nsu.fit.konstantinov.task_2_1_1.implementations.*
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)

class AllImplmplsTest {
    val testData3 = arrayListOf(
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

    val testData4 = arrayListOf(
        6997901,
        6997927,
        6997937,
        6997967,
        6998009,
    ).apply {
        for (i in 0..2000) {
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

    val testData5 = arrayListOf(
        6997901,
        6997927,
        6997937,
        6997967,
        6998009,
    ).apply {
        for (i in 0..20000) {
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

    val testData6 = arrayListOf(
        6997901,
        6997927,
        6997937,
        6997967,
        6998009,
    ).apply {
        for (i in 0..200000) {
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
    fun testSerial3() {
        assertEquals(SerialImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testSerial4() {
        assertEquals(SerialImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testSerial5() {
        assertEquals(SerialImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testSerial6() {
        assertEquals(SerialImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun testMultiCoroutines3() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testMultiCoroutines4() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testMultiCoroutines5() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testMultiCoroutines6() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun testCoroutines3() {
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testCoroutines4() {
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testCoroutines5() {
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testCoroutines6() {
        assertEquals(CoroutinesImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun testParallelStream3() {
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testParallelStream4() {
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testParallelStream5() {
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testParallelStream6() {
        assertEquals(ParallelStreamImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun testThreads3() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testThreads4() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testThreads5() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testThreads6() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData6), false)
    }
}
