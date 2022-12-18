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

    @Test
    fun timeTest() {
        val test = Notebook()
        val from = convertLongToTime(System.currentTimeMillis())
        test.add("First")
        val to = convertLongToTime(System.currentTimeMillis())
        test.add("Second")
        test.add("Third")
        test.add("Fourth")
        assertEquals(arrayListOf("First"), test.show(from, to))
    }

    private fun convertLongToTime(time: Long) = SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS").format(Date(time))
}
