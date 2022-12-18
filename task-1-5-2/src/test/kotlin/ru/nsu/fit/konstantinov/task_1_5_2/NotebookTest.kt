package ru.nsu.fit.konstantinov.task_1_5_2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NotebookTest {
    @Test
    fun simpleTest() {
        val test = Notebook()
        test.add("First", "Second")
        test.add("Third")
        assertEquals(arrayListOf("First", "Second", "Third"), test.show())
        test.rm("Second")
        assertEquals(arrayListOf("First", "Third"), test.show())
        test.add("Second")
        assertEquals(arrayListOf("First", "Third", "Second"), test.show())
        test.add("Second")
        test.add("Second")
        assertEquals(arrayListOf("First", "Third", "Second", "Second", "Second"), test.show())
        test.rm("Second")
        test.rm("First")
        assertEquals(arrayListOf("Third"), test.show())
    }
}
