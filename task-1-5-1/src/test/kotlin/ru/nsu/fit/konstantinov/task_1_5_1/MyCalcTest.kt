package ru.nsu.fit.konstantinov.task_1_5_1

import org.junit.jupiter.api.Assertions
import kotlin.test.Test
import kotlin.test.assertEquals

class MyCalcTest {
    @Test
    fun test() {
        assertEquals(5.0, MyCalc.calculate("+ 2.0 3.0"))
        assertEquals(10.0, MyCalc.calculate("+ + 5.0 2.0 3.0"))
        assertEquals(2.0, MyCalc.calculate("+ - 3.0 3.0 2.0"))
        assertEquals(-3.0, MyCalc.calculate("* - 3.0 3.0 2.0"))
        assertEquals(1.0, MyCalc.calculate("/ + 5.0 3.0 2.0"))
        Assertions.assertThrows(Exception::class.java) {
            MyCalc.calculate("L 2.0 3.0")
        }
    }
}
