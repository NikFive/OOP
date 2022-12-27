package ru.nsu.fit.konstantinov.task_1_5_2

import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals

class NotebookTest {
    @Test
    fun simpleTest() {
        val test = Notebook()
        test.add("First", "Second")
        test.add("Third")
        assertEquals(mutableListOf("First", "Second", "Third"), test.show())
        test.rm("Second")
        assertEquals(mutableListOf("First", "Third"), test.show())
        test.add("Second")
        assertEquals(mutableListOf("First", "Third", "Second"), test.show())
        test.add("Second")
        test.add("Second")
        assertEquals(mutableListOf("First", "Third", "Second", "Second", "Second"), test.show())
        test.rm("Second")
        test.rm("First")
        assertEquals(mutableListOf("Third"), test.show())
    }

    @Test
    fun timeTest() {
        val test = Notebook()
        val from = convertLongToTime(System.currentTimeMillis())
        test.add("First")
        val to = convertLongToTime(System.currentTimeMillis())
        test.add("Second")
        test.add("Third")
        test.add("Fourth")
        assertEquals(mutableListOf("First"), test.show(from, to))
        assertEquals(mutableListOf("First"), test.show(from, to, "First"))
        assertEquals(mutableListOf(), test.show(from, to, "Second"))
    }

    private fun convertLongToTime(time: Long) = SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS").format(Date(time))
}
