package ru.nsu.fit.konstantinov.task_1_3_1

import kotlin.test.Test
import kotlin.test.assertEquals

class FindSubStringTest {
    @Test
    fun testSimple() {
        val subString = "три"
        val path = "src/test/kotlin/ru/nsu/fit/konstantinov/task_1_3_1/input.txt"
        assertEquals(FindSubString().findSubStringInFile(path, subString), arrayListOf(8, 32, 54, 76, 106, 128, 150, 172, 198, 220, 242, 264, 286, 310, 332, 354, 376, 402, 424, 446, 468))
    }
}
