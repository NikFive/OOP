package ru.nsu.fit.konstantinov.task_1_2_3

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MyGraphTest {
    @Test
    fun testSimple() {
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.connect("A", "C")
        graph.connect("B", "D")
        graph.connect("B", "C")
        assertContentEquals(listOf("D", "C"), graph.neighbors("B"))
    }

    @Test
    fun testDFS() {
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("C", "D")
        assertEquals(3, graph.dfs("A", "D"))
        assertEquals(0, graph.dfs("A", "A"))
    }

    @Test
    fun testBFS() {
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("C", "D")
        assertEquals(3, graph.bfs("A", "D"))
        assertEquals(0, graph.bfs("A", "A"))
    }
}
