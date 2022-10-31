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
    private val obj = Json.parseToJsonElement(inputString).jsonArray
    fun parseAdjacencyList(): AdjacencyList<String, String> {
        val result: AdjacencyList<String, String> = AdjacencyList()
        val vertices: HashMap<String, Vertex<String>> = HashMap()
        for (i in obj) {
            val obj3 = Json.decodeFromJsonElement<JSONAdjacencyList>(i)
            vertices[obj3.elem] = Vertex(obj3.elem)
            result.addVertex(vertices[obj3.elem])
        }
        for (i in obj) {
            val obj3 = Json.decodeFromJsonElement<JSONAdjacencyList>(i)
            for (j in obj3.neighbors) {
                result.addEdge(Edge(obj3.elem + j.end, j.weight, vertices[obj3.elem]!!, vertices[j.end]!!))
            }
        }
        return result
    }

    @Serializable
    data class JSONAdjacencyList(val elem: String, val neighbors: ArrayList<NeighborsList>)

    @Serializable
    data class NeighborsList(val end: String, val weight: Int)
}
