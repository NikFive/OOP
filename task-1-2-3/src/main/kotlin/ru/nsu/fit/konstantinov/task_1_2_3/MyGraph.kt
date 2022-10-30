package ru.nsu.fit.konstantinov.task_1_2_3

interface Graph<V, E> {
    val vertexes: MutableSet<Vertex<V>?>
    val edgesNumber: Int
    val edges: MutableSet<Edge<V, E>?>
    val vertexNumber: Int
    fun addVertex(vertex: Vertex<V>?): Boolean
    fun deleteVertex(vertex: Vertex<V>?)
    fun addEdge(edge: Edge<V, E>?)
    fun deleteEdge(edge: Edge<V, E>?)
    fun getAdjVertexes(vertex: Vertex<V>?): MutableSet<Vertex<V>>
    fun getVertexElement(vertex: Vertex<V>?): V?
    fun setVertexElement(vertex: Vertex<V>?, newElem: V)
    fun getVertexDegree(vertex: Vertex<V>?): Int
}

data class Edge<T, T1>(val name: T1, val weight: Int, var start: Vertex<T>, var end: Vertex<T>)

class Vertex<T>(var elem: T) {
    override fun toString(): String {
        return elem.toString()
    }
}
