package ru.nsu.fit.konstantinov.task_1_2_2

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MyTreeTest {
    @Test
    fun testAddAddAll() {
        val tree = MyTree(1)
        assertEquals(tree.isEmpty(), false)
        tree.add(2)
        val nodeB = tree.add(3)
        tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        tree.addAll(nodeB, arrayOf(6, 7, 8).toList())
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 4, 0, 6, 7, 8)
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
        tree.addAll(arrayOf(6, 7, 8).toList())
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 2, 3, 4, 0, 6, 7, 8)
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
        assertEquals(true, tree.containsAll(arrayOf(1,2,3).toList()))
        assertEquals(false, tree.containsAll(arrayOf(-100, 3).toList()))
    }

    @Test
    fun testRemove() {
        val tree = MyTree(1)
        assertEquals(false, tree.isEmpty())
        tree.add(2)
        tree.add(8)
        val nodeB = tree.add(3)
        val nodeA = tree.add(nodeB, 4)
        tree.add(nodeB, 0)
        tree.add(nodeA, -1)
        assertEquals(true, tree.remove(nodeB, 4))
        assertEquals(true, tree.remove(3))
        assertEquals(true, tree.removeAll(arrayOf(2, 8).toList()))
        var array = emptyArray<Int>()
        val arrayRes = arrayOf(1, 0, -1)
        for (i in tree) {
            array += i
        }
        assertContentEquals(array, arrayRes)
        tree.clear()
        assertEquals(tree.isEmpty(), true)
    }
}
