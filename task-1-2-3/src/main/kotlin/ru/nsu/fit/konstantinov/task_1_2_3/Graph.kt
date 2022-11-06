package ru.nsu.fit.konstantinov.task_1_2_3

interface Graph<V, E> {
    val vertices: MutableSet<Vertex<V>?>
    val edgesNumber: Int
    val edges: MutableSet<Edge<V, E>?>
    val verticesNumber: Int
    fun addVertex(vertex: Vertex<V>?): Boolean
    fun deleteVertex(vertex: Vertex<V>?)
    fun addEdge(edge: Edge<V, E>?)
    fun deleteEdge(edge: Edge<V, E>?)
    fun getAdjacentVertices(vertex: Vertex<V>?): MutableSet<Vertex<V>>
    fun getVertexElement(vertex: Vertex<V>?): V?
    fun setVertexElement(vertex: Vertex<V>?, newElem: V)
    fun getVertexDegree(vertex: Vertex<V>?): Int
    fun containsVertex(vertex: Vertex<V>?): Boolean
    fun containsEdge(edge: Edge<V, E>?): Boolean
    fun dijkstraAlgorithm(startVertex: Vertex<V>): HashMap<Vertex<V>, Int> {
        val result: HashMap<Vertex<V>, Int> = HashMap()
        val verticesList: MutableList<Vertex<V>> = ArrayList()
        for (vertex in vertices) {
            vertex?.also { result[it] = Int.MAX_VALUE }
        }
        result[startVertex] = 0
        var minimalVertex: Vertex<V>? = null
        verticesList.add(startVertex)
        while (verticesList.isNotEmpty()) {
            var minimalValue = Int.MAX_VALUE
            for (vertex in verticesList) {
                result[vertex]?.also {
                    if (it < minimalValue) {
                        minimalVertex = vertex
                        minimalValue = it
                    }
                }
            }
            verticesList.remove(minimalVertex)
            for (minimalEdges in edges) {
                minimalEdges?.also {
                    if (it.start == minimalVertex && (it.weight + minimalValue) < (result[it.end] ?: 0)) {
                        result[it.end] = it.weight + minimalValue
                        verticesList.add(it.end)
                    }
                }
            }
        }
        return result
    }
}

data class Edge<T, T1>(val value: T1, val weight: Int, var start: Vertex<T>, var end: Vertex<T>)

class Vertex<T>(var elem: T) {
    override fun toString(): String {
        return elem.toString()
    }
}
