package ru.nsu.fit.konstantinov.task_1_5_1

import org.junit.jupiter.api.Assertions
import kotlin.test.Test
import kotlin.test.assertEquals

class MyCalcTest {
    @Test
    fun test() {
        assertEquals(5.0, MyCalc.CalcOperation.operation("+", 2.0, 3.0))
        assertEquals(-1.0, MyCalc.CalcOperation.operation("-", 2.0, 3.0))
        Assertions.assertThrows(Exception::class.java) {
            MyCalc.CalcOperation.operation("A", 2.0, 3.0)
        }
    }
}
