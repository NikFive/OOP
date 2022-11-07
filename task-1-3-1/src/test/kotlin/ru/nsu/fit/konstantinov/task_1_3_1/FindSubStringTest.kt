package ru.nsu.fit.konstantinov.task_1_3_1

import kotlin.test.Test
import kotlin.test.assertEquals

class FindSubStringTest {
    @Test
    fun testSimple() {
        val subString = "thr"
        val subString2 = "zero"
        val path = "src/test/kotlin/ru/nsu/fit/konstantinov/task_1_3_1/input.txt"
        assertEquals(
            FindSubString().findSubStringInFile(path, subString), arrayListOf(
                8, 37, 62, 82, 107, 125, 150, 175, 200, 219, 244, 266, 291, 309, 334, 420, 445
            )
        )
        assertEquals(FindSubString().findSubStringInFile(path, subString2), arrayListOf())
    }
}
