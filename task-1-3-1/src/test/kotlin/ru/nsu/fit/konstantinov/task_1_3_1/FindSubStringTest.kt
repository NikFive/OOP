package ru.nsu.fit.konstantinov.task_1_3_1

import kotlin.test.Test
import kotlin.test.assertEquals


class FindSubStringTest {
    @Test
    fun testSimple() {
        val txt = "Я ем пирог!"
        val pat = "пирог"
        assertEquals(FindSubString().kmpAlgorithm(pat, txt), arrayListOf(5))
    }
}
