package ru.nsu.fit.konstantinov.task_1_2_2

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MyTreeTest {
    @Test
    fun testSimple() {
        val tree = MyTreeNode("numbers")
        val minus = MyTreeNode("-")
        val plus = MyTreeNode("+")

        val minusFive = MyTreeNode("-5")
        val minusTwo = MyTreeNode("-2")
        val minusTen = MyTreeNode("-10")

        val five = MyTreeNode("5")
        val two = MyTreeNode("2")
        val three = MyTreeNode("3")

        tree.add(minus)
        tree.add(plus)

        minus.add(minusFive)
        minus.add(minusTwo)
        minus.add(minusTen)

        plus.add(five)
        plus.add(two)
        plus.add(three)

        var checkArray = emptyArray<String>()
        val exampleArray = arrayOf("numbers", "-", "-5", "-2", "-10", "+", "5", "2", "3")
        tree.forEachDepthFirst { checkArray += it.value }
        assertContentEquals(exampleArray, checkArray)

        assertEquals("-5", tree.search("-5"))
        assertEquals(null, tree.search("0"))
    }
}
