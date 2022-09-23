package ru.nsu.fit.konstantinov.task_1_2_1

import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class MyStackTest {
    @Test
    fun testPushAndPop() {
        val stackCheck: MyStack<Int> = MyStack()
        stackCheck.push(1)
        stackCheck.push(2)
        stackCheck.push(3)
        assertEquals(3, stackCheck.count())
        assertEquals(3, stackCheck.pop())
        assertEquals(2, stackCheck.pop())
        assertEquals(1, stackCheck.pop())
        stackCheck.push(4)
        assertEquals(4, stackCheck.pop())
        assertEquals(0, stackCheck.count())
    }

    @Test
    fun testPushStackAndPopStack() {
        val stackFirst: MyStack<Int> = MyStack()
        val stackSecond: MyStack<Int> = MyStack()
        stackFirst.push(1)
        stackFirst.push(2)
        stackFirst.push(3)
        assertEquals(3, stackFirst.count())
        stackSecond.push(4)
        stackSecond.push(5)
        assertEquals(2, stackSecond.count())
        stackFirst.pushStack(stackSecond)
        assertEquals(5, stackFirst.count())
        val stackThird: MyStack<Int?> = stackFirst.popStack(4)
        assertEquals(4, stackThird.count())
        assertEquals(5, stackThird.pop())
        assertEquals(4, stackThird.pop())
        assertEquals(3, stackThird.pop())
        assertEquals(2, stackThird.pop())
        stackThird.push(6)
        assertEquals(6, stackThird.pop())
        assertEquals(0, stackThird.count())
    }

    @Test
    fun testExceptions() {
        val stackCheck: MyStack<Int> = MyStack()
        stackCheck.push(1)
        stackCheck.push(2)
        stackCheck.push(3)
        assertEquals(3, stackCheck.count())
        assertThrows(IllegalStateException::class.java) {
            stackCheck.popStack(4)
        }
        assertEquals(3, stackCheck.pop())
        assertEquals(2, stackCheck.pop())
        assertEquals(1, stackCheck.pop())
        stackCheck.push(4)
        assertEquals(4, stackCheck.pop())
        assertEquals(0, stackCheck.count())
        assertThrows(IllegalStateException::class.java) {
            stackCheck.pop()
        }
    }
}
