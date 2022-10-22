package ru.nsu.fit.konstantinov.task_1_2_2

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MyTreeTest {
    @Test
    fun testSimple() {
        val tree = MyTree(1)
        assertEquals(tree.isEmpty(), false)
        tree.add(2)
        val nodeB = tree.add(3)
        tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 4, 0)
        for (i in tree) {
            array += i
        }
        assertContentEquals(array, arrayRes)
    }

    @Test
    fun testEmptyTree() {
        val tree = MyTree<Int>()
        assertEquals(tree.isEmpty(), true)
        val nodeA = tree.add(1)
        tree.add(nodeA, 2)
        val nodeB = tree.add(3)
        tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 4, 0)
        for (i in tree) {
            array += i
        }
        assertContentEquals(array, arrayRes)
    }

    @Test
    fun testContains() {
        val tree = MyTree(1)
        assertEquals(false, tree.isEmpty())
        tree.add(2)
        val nodeB = tree.add(3)
        tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        var array = emptyArray<Int>()
        for (i in tree) {
            array += i
        }
        assertEquals(true, tree.contains(1))
        assertEquals(false, tree.contains(-1))
    }

    @Test
    fun testRemove() {
        val tree = MyTree(1)
        assertEquals(false, tree.isEmpty())
        tree.add(2)
        val nodeB = tree.add(3)
        val nodeA = tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        tree.add(nodeA, -1)
        assertEquals(true, tree.remove(nodeB, 4))
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 0, -1)
        for (i in tree) {
            array += i
        }
        assertContentEquals(array, arrayRes)
    }
}
