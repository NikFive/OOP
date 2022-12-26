package ru.nsu.fit.konstantinov.task_1_3_1

import org.junit.jupiter.api.BeforeEach
import java.io.InputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class FindSubStringTest {
    private var stream: InputStream? = null

    @BeforeEach
    fun createStream() {
        stream = ClassLoader.getSystemResourceAsStream("input.txt")
    }

    @Test
    fun testSimpleKMP() {
        val subString = "thr"
        assertEquals(arrayListOf(
            8, 37, 62, 82, 107, 125, 150, 175, 200, 219, 244, 266, 291, 309, 334, 420, 445
        ), stream?.let { FindSubString().findSubStringInStreamKMP(it, subString) })
    }

    @Test
    fun testSimpleAho() {
        val subString = "thr"
        assertEquals(arrayListOf(
            8, 37, 62, 82, 107, 125, 150, 175, 200, 219, 244, 266, 291, 309, 334, 420, 445
        ), stream?.let { FindSubString().findSubStringInStreamAho(it, subString) })
    }

    @Test
    fun testEmptyKMP() {
        val subString2 = "zero"
        assertEquals(stream?.let { FindSubString().findSubStringInStreamKMP(it, subString2) }, arrayListOf())
    }

    @Test
    fun testEmptyAho() {
        val subString2 = "zero"
        assertEquals(stream?.let { FindSubString().findSubStringInStreamKMP(it, subString2) }, arrayListOf())
    }
}
