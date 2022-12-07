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
        assertEquals(0.0, MyCalc.calculate("* - 3.0 3.0 2.0"))
        assertEquals(4.0, MyCalc.calculate("/ + 5.0 3.0 2.0"))
        assertEquals(9.0, MyCalc.calculate("+ - 64 * 15 4 / 20 4"))
        assertEquals(81.0, MyCalc.calculate("pow + - 64 * 15 4 / 20 4 2"))
        assertEquals(2.0, MyCalc.calculate("log + - 64 * 15 4 / 20 4 3"))
        assertEquals(3.0, MyCalc.calculate("sqrt + - 64 * 15 4 / 20 4 3"))
        assertEquals(0.0, MyCalc.calculate("sin + - 1 2 1"))
        assertEquals(1.0, MyCalc.calculate("cos + - 1 2 1"))
        Assertions.assertThrows(Exception::class.java) {
            MyCalc.calculate("L 2.0 3.0")
        }
    }
}
