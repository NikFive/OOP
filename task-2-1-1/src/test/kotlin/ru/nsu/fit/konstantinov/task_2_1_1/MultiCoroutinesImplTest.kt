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

class MultiCoroutinesImplImplTest {
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
    fun testDefaultMultiCoroutines3() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData3), false)
    }

    @Test
    @Benchmark
    fun testDefaultMultiCoroutines4() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData4), false)
    }

    @Test
    @Benchmark
    fun testDefaultMultiCoroutines5() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData5), false)
    }

    @Test
    @Benchmark
    fun testDefaultMultiCoroutines6() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData6), false)
    }

    @Test
    @Benchmark
    fun test16MultiCoroutines3() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData3, 16), false)
    }

    @Test
    @Benchmark
    fun test16MultiCoroutines4() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData4, 16), false)
    }

    @Test
    @Benchmark
    fun test16MultiCoroutines5() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData5, 16), false)
    }

    @Test
    @Benchmark
    fun test16MultiCoroutines6() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData6, 16), false)
    }

    @Test
    @Benchmark
    fun test32MultiCoroutines3() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData3, 32), false)
    }

    @Test
    @Benchmark
    fun test32MultiCoroutines4() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData4, 32), false)
    }

    @Test
    @Benchmark
    fun test32MultiCoroutines5() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData5, 32), false)
    }

    @Test
    @Benchmark
    fun test32MultiCoroutines6() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData6, 32), false)
    }

    @Test
    @Benchmark
    fun test64MultiCoroutines3() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData3, 64), false)
    }

    @Test
    @Benchmark
    fun test64MultiCoroutines4() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData4, 64), false)
    }

    @Test
    @Benchmark
    fun test64MultiCoroutines5() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData5, 64), false)
    }

    @Test
    @Benchmark
    fun test64MultiCoroutines6() {
        assertEquals(MultiCoroutinesImpl.containsPrimeNumbers(testData6, 64), false)
    }
}
