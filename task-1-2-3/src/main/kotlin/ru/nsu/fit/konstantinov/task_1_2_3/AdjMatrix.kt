package ru.nsu.fit.konstantinov.task_1_2_3

class AdjMatrix<V, E> : Graph<V, E> {
    override val vertices: MutableSet<Vertex<V>?> = HashSet()
    override val edgesNumber: Int
        get() = edges.size

    override val edges: MutableSet<Edge<V, E>?> = HashSet()
    override val verticesNumber: Int
        get() = vertices.size

    private val matrix: HashMap<Vertex<V>, HashMap<Vertex<V>, Int>> = HashMap()

    override fun getVertexDegree(vertex: Vertex<V>?): Int = getAdjacentVertices(vertex).size

    override fun setVertexElement(vertex: Vertex<V>?, newElem: V) {
        vertex?.let { it.elem = newElem }
    }

    override fun getVertexElement(vertex: Vertex<V>?): V? = vertex?.elem

    override fun getAdjacentVertices(vertex: Vertex<V>?): MutableSet<Vertex<V>> {
        require(vertices.contains(vertex)) { "Vertex does not in the graph." }
        val adjVertexes: MutableSet<Vertex<V>> = HashSet()
        val matrix = matrix[vertex]
        for (newVertex in vertices) {
            matrix?.get(newVertex)?.let {
                if (it > 0) {
                    newVertex?.let { it1 -> adjVertexes.add(it1) }
                }
            }
        }
        return adjVertexes
    }

    override fun deleteEdge(edge: Edge<V, E>?) {
        edge?.let {
            if (vertices.contains(it.end).not() || vertices.contains(it.start).not()) {
                throw IllegalArgumentException("Vertexes does not in the graph.")
            }
            edges.remove(it)
            matrix[it.start]?.put(it.end, 0)
            matrix[it.end]?.put(it.start, 0)
        }
    }

    override fun addEdge(edge: Edge<V, E>?) {
        edge?.let {
            if (vertices.contains(it.end).not() || vertices.contains(it.start).not()) {
                throw IllegalArgumentException("Vertexes does not in the graph.")
            }
            if (!edges.contains(it)) {
                edges.add(it)
                matrix[it.start]?.put(it.end, 1)
                matrix[it.end]?.put(it.start, 1)
            }
        }
    }

    override fun deleteVertex(vertex: Vertex<V>?) {
        if (vertices.contains(vertex)) {
            vertices.remove(vertex)
            matrix.remove(vertex)
            edges.removeIf { edge ->
                edge?.let { it.start == vertex || it.end == vertex } ?: false
            }
            for (customVertex in vertices) {
                matrix[customVertex]?.remove(vertex)
            }
        }
    }

    override fun addVertex(vertex: Vertex<V>?): Boolean {
        return if (!vertices.contains(vertex)) {
            vertex?.let { initVertex(it) }
            true
        } else {
            false
        }
    }

    private fun initVertex(vertex: Vertex<V>) {
        vertices.add(vertex)
        matrix[vertex] = HashMap()
        for (newVertex in vertices) {
            matrix[newVertex]?.put(vertex, 0)
            newVertex?.let { matrix[vertex]?.put(it, 0) }
        }
    }
}
