package ru.nsu.fit.konstantinov.task_1_2_3

class AdjList<V, E> : Graph<V, E> {
    override val vertexes: MutableSet<Vertex<V>?> = HashSet()
    override val vertexNumber: Int
        get() = vertexes.size
    override val edges: MutableSet<Edge<V, E>?> = HashSet()
    override val edgesNumber: Int
        get() = edges.size
    private val listRows: HashMap<Vertex<V>, MutableSet<Vertex<V>>> = HashMap()

    override fun getVertexDegree(vertex: Vertex<V>?): Int = getAdjVertexes(vertex).size

    override fun setVertexElement(vertex: Vertex<V>?, newElem: V) {
        vertex?.let { it.elem = newElem }
    }

    override fun getVertexElement(vertex: Vertex<V>?): V? = vertex?.elem

    override fun getAdjVertexes(vertex: Vertex<V>?): MutableSet<Vertex<V>> =
        (listRows[vertex] ?: emptySet()) as MutableSet<Vertex<V>>

    override fun deleteEdge(edge: Edge<V, E>?) {
        edges.remove(edge)
        edge?.let {
            listRows[it.start]?.remove(it.end)
            listRows[it.end]?.remove(it.start)
        }
    }

    override fun addEdge(edge: Edge<V, E>?) {
        edge?.let {
            if (vertexes.contains(it.end).not() || vertexes.contains(it.start).not()) {
                throw IllegalArgumentException("Vertexes does not in the graph.")
            }
            edges.add(edge)
            listRows[it.start]?.add(it.end)
            listRows[it.end]?.add(it.start)
        }
    }

    override fun deleteVertex(vertex: Vertex<V>?) {
        listRows.remove(vertex)
        vertexes.remove(vertex)
        edges.removeIf { edge ->
            edge?.let { it.start == vertex || it.end == vertex } ?: false
        }
    }

    override fun addVertex(vertex: Vertex<V>?): Boolean {
        return if (!vertexes.contains(vertex)) {
            val adjVertexes: MutableSet<Vertex<V>> = HashSet()
            vertexes.add(vertex)
            vertex?.let { listRows.put(it, adjVertexes) }
            true
        } else {
            false
        }
    }
}
