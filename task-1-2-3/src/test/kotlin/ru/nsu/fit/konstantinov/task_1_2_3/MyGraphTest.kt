package ru.nsu.fit.konstantinov.task_1_2_3

import kotlin.test.Test
import kotlin.test.assertEquals


class MyGraphTest {
    private var matrixForTest: IncMatrix<String, String>? = IncMatrix()

    @Test
    fun testIncMatrix() {
        val firstVertex = Vertex("1")
        val secondVertex = Vertex("2")
        val thirdVertex = Vertex("3")
        val fourthVertex = Vertex("4")
        val fifthVertex = Vertex("5")
        val firstEdge = Edge("1", 15, firstVertex, secondVertex)
        val secondEdge = Edge("2", 51, firstVertex, thirdVertex)
        val thirdEdge = Edge("3", 13, thirdVertex, fourthVertex)
        val fourthEdge = Edge("4", 31, fourthVertex, fifthVertex)
        matrixForTest?.addVertex(firstVertex)
        matrixForTest?.addVertex(secondVertex)
        matrixForTest?.addVertex(thirdVertex)
        matrixForTest?.addVertex(fourthVertex)
        matrixForTest?.addVertex(fifthVertex)
        matrixForTest?.addEdge(firstEdge)
        matrixForTest?.addEdge(secondEdge)
        matrixForTest?.addEdge(thirdEdge)
        matrixForTest?.addEdge(fourthEdge)
        assertEquals(4, matrixForTest?.edgesNumber)
        assertEquals(5, matrixForTest?.vertexNumber)
        assertEquals("1", matrixForTest?.getVertexElement(firstVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(firstVertex))
        assertEquals("2", matrixForTest?.getVertexElement(secondVertex))
        assertEquals(1, matrixForTest?.getVertexDegree(secondVertex))
        assertEquals("3", matrixForTest?.getVertexElement(thirdVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(thirdVertex))
        assertEquals("4", matrixForTest?.getVertexElement(fourthVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(fourthVertex))
        matrixForTest?.deleteVertex(firstVertex)
        assertEquals(4, matrixForTest?.vertexNumber)
        assertEquals(2, matrixForTest?.edgesNumber)
        matrixForTest?.deleteEdge(fourthEdge)
        assertEquals(4, matrixForTest?.vertexNumber)
        assertEquals(1, matrixForTest?.edgesNumber)
        assertEquals(mutableSetOf(thirdVertex), (matrixForTest?.getAdjVertexes(fourthVertex)))
    }

    private var matrixForTest2: AdjMatrix<String, String>? = AdjMatrix()

    @Test
    fun testAdjMatrix() {
        val firstVertex = Vertex("1")
        val secondVertex = Vertex("2")
        val thirdVertex = Vertex("3")
        val fourthVertex = Vertex("4")
        val fifthVertex = Vertex("5")
        val firstEdge = Edge("1", 15, firstVertex, secondVertex)
        val secondEdge = Edge("2", 51, firstVertex, thirdVertex)
        val thirdEdge = Edge("3", 13, thirdVertex, fourthVertex)
        val fourthEdge = Edge("4", 31, fourthVertex, fifthVertex)
        matrixForTest2?.addVertex(firstVertex)
        matrixForTest2?.addVertex(secondVertex)
        matrixForTest2?.addVertex(thirdVertex)
        matrixForTest2?.addVertex(fourthVertex)
        matrixForTest2?.addVertex(fifthVertex)
        matrixForTest2?.addEdge(firstEdge)
        matrixForTest2?.addEdge(secondEdge)
        matrixForTest2?.addEdge(thirdEdge)
        matrixForTest2?.addEdge(fourthEdge)
        assertEquals(4, matrixForTest2?.edgesNumber)
        assertEquals(5, matrixForTest2?.vertexNumber)
        assertEquals("1", matrixForTest2?.getVertexElement(firstVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(firstVertex))
        assertEquals("2", matrixForTest2?.getVertexElement(secondVertex))
        assertEquals(1, matrixForTest2?.getVertexDegree(secondVertex))
        assertEquals("3", matrixForTest2?.getVertexElement(thirdVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(thirdVertex))
        assertEquals("4", matrixForTest2?.getVertexElement(fourthVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(fourthVertex))
        matrixForTest2?.deleteVertex(firstVertex)
        assertEquals(4, matrixForTest2?.vertexNumber)
        assertEquals(2, matrixForTest2?.edgesNumber)
        matrixForTest2?.deleteEdge(fourthEdge)
        assertEquals(4, matrixForTest2?.vertexNumber)
        assertEquals(1, matrixForTest2?.edgesNumber)
        assertEquals(mutableSetOf(thirdVertex), (matrixForTest2?.getAdjVertexes(fourthVertex)))
    }
}
