package ru.nsu.fit.konstantinov.task_1_2_3

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import java.io.BufferedReader
import java.io.File

class ParseGraph(text: String) {
    private val bufferedReader: BufferedReader = File(text).bufferedReader()
    private val inputString = bufferedReader.use { it.readText() }
    private val jsonData = Json.parseToJsonElement(inputString).jsonArray
    fun parseAdjacencyList(): AdjacencyList<String, String> {
        val result: AdjacencyList<String, String> = AdjacencyList()
        val vertices: HashMap<String, Vertex<String>> = HashMap()
        for (i in jsonData) {
            val jsonElem = Json.decodeFromJsonElement<JSONAdjacencyList>(i)
            vertices[jsonElem.elem] = Vertex(jsonElem.elem)
            result.addVertex(vertices[jsonElem.elem])
        }
        for (i in jsonData) {
            val jsonElem = Json.decodeFromJsonElement<JSONAdjacencyList>(i)
            for (j in jsonElem.neighbors) {
                result.addEdge(Edge(jsonElem.elem + j.end, j.weight, vertices[jsonElem.elem]!!, vertices[j.end]!!))
            }
        }
        return result
    }

    @Serializable
    private data class JSONAdjacencyList(val elem: String, val neighbors: ArrayList<NeighborsList>)

    @Serializable
    private data class NeighborsList(val end: String, val weight: Int)


    fun parseAdjacencyMatrix(): AdjacencyMatrix<String, String> {
        val result: AdjacencyMatrix<String, String> = AdjacencyMatrix()
        val vertices: MutableList<Vertex<String>> = ArrayList()
        for (i in jsonData) {
            val jsonElem = Json.decodeFromJsonElement<JSONAdjacencyMatrix>(i)
            val vertex = Vertex(jsonElem.elem)
            vertices.add(vertex)
            result.addVertex(vertex)
        }
        var countI = 0
        for (i in jsonData) {
            val jsonElem = Json.decodeFromJsonElement<JSONAdjacencyMatrix>(i)
            var countJ = 0
            for (j in jsonElem.vertices) {
                if (j.weight != 0) {
                    result.addEdge(
                        Edge(
                            jsonElem.elem + vertices[countJ].elem, j.weight, vertices[countI], vertices[countJ]
                        )
                    )
                }
                countJ++
            }
            countI++
        }
        return result
    }

    @Serializable
    private data class JSONAdjacencyMatrix(val elem: String, val vertices: ArrayList<VerticesList>)

    @Serializable
    private data class VerticesList(val weight: Int)
}
