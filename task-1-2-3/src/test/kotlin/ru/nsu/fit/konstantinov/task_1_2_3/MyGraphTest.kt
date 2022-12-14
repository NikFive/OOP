package ru.nsu.fit.konstantinov.task_1_2_3

import org.junit.jupiter.api.Assertions
import java.io.FileNotFoundException
import kotlin.test.Test
import kotlin.test.assertEquals


class MyGraphTest {
    private var matrixForTest: IncidentMatrix<String, String>? = IncidentMatrix()

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
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            matrixForTest?.addEdge(Edge("4", 31, Vertex("lol"), Vertex("kek")))
        }
        assertEquals(4, matrixForTest?.edgesNumber)
        assertEquals(5, matrixForTest?.verticesNumber)
        assertEquals("1", matrixForTest?.getVertexElement(firstVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(firstVertex))
        assertEquals("2", matrixForTest?.getVertexElement(secondVertex))
        assertEquals(1, matrixForTest?.getVertexDegree(secondVertex))
        assertEquals("3", matrixForTest?.getVertexElement(thirdVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(thirdVertex))
        assertEquals("4", matrixForTest?.getVertexElement(fourthVertex))
        assertEquals(2, matrixForTest?.getVertexDegree(fourthVertex))
        matrixForTest?.deleteVertex(firstVertex)
        assertEquals(4, matrixForTest?.verticesNumber)
        assertEquals(2, matrixForTest?.edgesNumber)
        matrixForTest?.deleteEdge(fourthEdge)
        assertEquals(4, matrixForTest?.verticesNumber)
        assertEquals(1, matrixForTest?.edgesNumber)
        assertEquals(true, matrixForTest?.containsEdge(thirdEdge))
        assertEquals(false, matrixForTest?.containsEdge(fourthEdge))
        assertEquals(true, matrixForTest?.containsVertex(thirdVertex))
        assertEquals(false, matrixForTest?.containsVertex(firstVertex))
        assertEquals(mutableSetOf(thirdVertex), (matrixForTest?.getAdjacentVertices(fourthVertex)))
    }

    private var matrixForTest2: AdjacencyMatrix<String, String>? = AdjacencyMatrix()

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
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            matrixForTest2?.addEdge(Edge("4", 31, Vertex("lol"), Vertex("kek")))
        }
        assertEquals(4, matrixForTest2?.edgesNumber)
        assertEquals(5, matrixForTest2?.verticesNumber)
        assertEquals("1", matrixForTest2?.getVertexElement(firstVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(firstVertex))
        assertEquals("2", matrixForTest2?.getVertexElement(secondVertex))
        assertEquals(1, matrixForTest2?.getVertexDegree(secondVertex))
        assertEquals("3", matrixForTest2?.getVertexElement(thirdVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(thirdVertex))
        assertEquals("4", matrixForTest2?.getVertexElement(fourthVertex))
        assertEquals(2, matrixForTest2?.getVertexDegree(fourthVertex))
        matrixForTest2?.deleteVertex(firstVertex)
        assertEquals(4, matrixForTest2?.verticesNumber)
        assertEquals(2, matrixForTest2?.edgesNumber)
        matrixForTest2?.deleteEdge(fourthEdge)
        assertEquals(4, matrixForTest2?.verticesNumber)
        assertEquals(1, matrixForTest2?.edgesNumber)
        assertEquals(true, matrixForTest2?.containsEdge(thirdEdge))
        assertEquals(false, matrixForTest2?.containsEdge(fourthEdge))
        assertEquals(true, matrixForTest2?.containsVertex(thirdVertex))
        assertEquals(false, matrixForTest2?.containsVertex(firstVertex))
        assertEquals(mutableSetOf(thirdVertex), (matrixForTest2?.getAdjacentVertices(fourthVertex)))
    }

    private var matrixForTest3: AdjacencyList<String, String>? = AdjacencyList()

    @Test
    fun testAdjList() {
        val firstVertex = Vertex("1")
        val secondVertex = Vertex("2")
        val thirdVertex = Vertex("3")
        val fourthVertex = Vertex("4")
        val fifthVertex = Vertex("5")
        val firstEdge = Edge("1", 15, firstVertex, secondVertex)
        val secondEdge = Edge("2", 51, firstVertex, thirdVertex)
        val thirdEdge = Edge("3", 13, thirdVertex, fourthVertex)
        val fourthEdge = Edge("4", 31, fourthVertex, fifthVertex)
        matrixForTest3?.addVertex(firstVertex)
        matrixForTest3?.addVertex(secondVertex)
        matrixForTest3?.addVertex(thirdVertex)
        matrixForTest3?.addVertex(fourthVertex)
        matrixForTest3?.addVertex(fifthVertex)
        matrixForTest3?.addEdge(firstEdge)
        matrixForTest3?.addEdge(secondEdge)
        matrixForTest3?.addEdge(thirdEdge)
        matrixForTest3?.addEdge(fourthEdge)
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            matrixForTest3?.addEdge(Edge("4", 31, Vertex("lol"), Vertex("kek")))
        }
        assertEquals(4, matrixForTest3?.edgesNumber)
        assertEquals(5, matrixForTest3?.verticesNumber)
        assertEquals("1", matrixForTest3?.getVertexElement(firstVertex))
        assertEquals(2, matrixForTest3?.getVertexDegree(firstVertex))
        assertEquals("2", matrixForTest3?.getVertexElement(secondVertex))
        assertEquals(1, matrixForTest3?.getVertexDegree(secondVertex))
        assertEquals("3", matrixForTest3?.getVertexElement(thirdVertex))
        assertEquals(2, matrixForTest3?.getVertexDegree(thirdVertex))
        assertEquals("4", matrixForTest3?.getVertexElement(fourthVertex))
        assertEquals(2, matrixForTest3?.getVertexDegree(fourthVertex))
        matrixForTest3?.deleteVertex(firstVertex)
        assertEquals(4, matrixForTest3?.verticesNumber)
        assertEquals(2, matrixForTest3?.edgesNumber)
        matrixForTest3?.deleteEdge(fourthEdge)
        assertEquals(4, matrixForTest3?.verticesNumber)
        assertEquals(1, matrixForTest3?.edgesNumber)
        assertEquals(true, matrixForTest3?.containsEdge(thirdEdge))
        assertEquals(false, matrixForTest3?.containsEdge(fourthEdge))
        assertEquals(true, matrixForTest3?.containsVertex(thirdVertex))
        assertEquals(false, matrixForTest3?.containsVertex(firstVertex))
        assertEquals(mutableSetOf(thirdVertex), (matrixForTest3?.getAdjacentVertices(fourthVertex)))
    }

    private var matrixForDijkstra: AdjacencyList<String, String>? = AdjacencyList()

    @Test
    fun testDijkstra() {
        val firstVertex = Vertex("A")
        val secondVertex = Vertex("B")
        val thirdVertex = Vertex("C")
        val fourthVertex = Vertex("D")
        val fifthVertex = Vertex("E")
        val firstEdge = Edge("AB", 100, firstVertex, secondVertex)
        val secondEdge = Edge("BC", 10, secondVertex, thirdVertex)
        val thirdEdge = Edge("CD", 8, thirdVertex, fourthVertex)
        val fourthEdge = Edge("ED", 4, fifthVertex, fourthVertex)
        val fifthEdge = Edge("AE", 5, firstVertex, fifthVertex)
        matrixForDijkstra?.addVertex(firstVertex)
        matrixForDijkstra?.addVertex(secondVertex)
        matrixForDijkstra?.addVertex(thirdVertex)
        matrixForDijkstra?.addVertex(fourthVertex)
        matrixForDijkstra?.addVertex(fifthVertex)
        matrixForDijkstra?.addEdge(firstEdge)
        matrixForDijkstra?.addEdge(secondEdge)
        matrixForDijkstra?.addEdge(thirdEdge)
        matrixForDijkstra?.addEdge(fourthEdge)
        matrixForDijkstra?.addEdge(fifthEdge)
        assertEquals(
            matrixForDijkstra?.dijkstraAlgorithm(firstVertex), hashMapOf(
                Pair(firstVertex, 0),
                Pair(secondVertex, 100),
                Pair(thirdVertex, 110),
                Pair(fourthVertex, 9),
                Pair(fifthVertex, 5)
            )
        )
    }

    @Test
    fun testParseAdjacencyList() {
        val testParse =
            ParseGraph("src/test/kotlin/ru/nsu/fit/konstantinov/task_1_2_3/AdjacencyList.json").parseAdjacencyList()
        Assertions.assertThrows(FileNotFoundException::class.java) {
            ParseGraph("123.json").parseAdjacencyList()
        }
        assertEquals(6, testParse.edgesNumber)
        assertEquals(6, testParse.verticesNumber)
    }

    @Test
    fun testParseAdjacencyMatrix() {
        val testParse =
            ParseGraph("src/test/kotlin/ru/nsu/fit/konstantinov/task_1_2_3/AdjacencyMatrix.json").parseAdjacencyMatrix()
        Assertions.assertThrows(FileNotFoundException::class.java) {
            ParseGraph("123.json").parseAdjacencyMatrix()
        }
        assertEquals(6, testParse.edgesNumber)
        assertEquals(6, testParse.verticesNumber)
    }
}
