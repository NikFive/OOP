package ru.nsu.fit.konstantinov.task_1_2_2

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MyTreeTest {
    @Test
    fun testSimple() {
        val tree = MyTree(1)
        tree.add(2)
        val nodeB = tree.add(3)
        tree.add(nodeB,4)
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 4)
        for (i in tree) {
            array += i
        }
        assertContentEquals(array, arrayRes)
    }
}
