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

class ThreadsImplTest {
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
    fun testDefaultThreads3() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testDefaultThreads4() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testDefaultThreads5() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testDefaultThreads6() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun test16Threads3() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData3, 16), false)
    }

    @Test
    @Benchmark
    fun test16Threads4() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData4, 16), false)
    }

    @Test
    @Benchmark
    fun test16Threads5() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData5, 16), false)
    }

    @Test
    @Benchmark
    fun test16Threads6() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData6, 16), false)
    }

    @Test
    @Benchmark
    fun test32Threads3() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData3, 32), false)
    }

    @Test
    @Benchmark
    fun test32Threads4() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData4, 32), false)
    }

    @Test
    @Benchmark
    fun test32Threads5() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData5, 32), false)
    }

    @Test
    @Benchmark
    fun test32Threads6() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData6, 32), false)
    }

    @Test
    @Benchmark
    fun test64Threads3() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData3, 64), false)
    }

    @Test
    @Benchmark
    fun test64Threads4() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData4, 64), false)
    }

    @Test
    @Benchmark
    fun test64Threads5() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData5, 64), false)
    }

    @Test
    @Benchmark
    fun test64Threads6() {
        assertEquals(ThreadsImpl.containsPrimeNumbers(testData6, 64), false)
    }
}
