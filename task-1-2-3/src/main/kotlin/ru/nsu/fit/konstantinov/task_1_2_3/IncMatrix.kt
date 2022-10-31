package ru.nsu.fit.konstantinov.task_1_2_3

class IncMatrix<V, E> : Graph<V, E> {
    override var vertices: MutableSet<Vertex<V>?> = HashSet()

    override var edges: MutableSet<Edge<V, E>?> = HashSet()
    override val verticesNumber: Int
        get() = vertices.size
    override val edgesNumber: Int
        get() = edges.size

    private var matrix: HashMap<Vertex<V>, HashMap<Edge<V, E>, Int>> = HashMap()

    override fun addVertex(vertex: Vertex<V>?): Boolean {
        return if (vertices.contains(vertex).not()) {
            vertex?.let { initVertex(it) }
            true
        } else {
            false
        }
    }

    override fun deleteVertex(vertex: Vertex<V>?) {
        val toDelete: ArrayList<Edge<V, E>> = ArrayList()
        vertices.remove(vertex)
        matrix.remove(vertex)
        for (edge in edges) {
            edge?.let {
                if (it.start == vertex || it.end == vertex) {
                    toDelete.add(it)
                    for (customVertex in vertices) {
                        matrix[customVertex]?.remove(it)
                    }
                }
            }
        }
        toDelete.forEach { edge -> edges.remove(edge) }
    }

    override fun addEdge(edge: Edge<V, E>?) {
        edge?.let {
            if (vertices.contains(it.end).not() || vertices.contains(it.start).not()) {
                throw IllegalArgumentException("Vertex does not in the graph.")
            }
            initEdge(it)
        }
    }

    override fun deleteEdge(edge: Edge<V, E>?) {
        edges.remove(edge)
        for (vertex in vertices) {
            matrix[vertex]?.remove(edge)
        }
    }

    override fun getAdjacentVertices(vertex: Vertex<V>?): MutableSet<Vertex<V>> {
        val vertexSet: MutableSet<Vertex<V>> = HashSet()
        for (edge in edges) {
            edge?.also {
                if (it.start == vertex) {
                    vertexSet.add(it.end)
                } else if (it.end == vertex) {
                    vertexSet.add(it.start)
                }
            }
        }
        return vertexSet
    }

    override fun getVertexElement(vertex: Vertex<V>?): V? = vertex?.elem

    override fun getVertexDegree(vertex: Vertex<V>?): Int = getAdjacentVertices(vertex).size

    override fun setVertexElement(vertex: Vertex<V>?, newElem: V) {
        vertex?.let { it.elem = newElem }
    }

    private fun initEdge(edge: Edge<V, E>) {
        edges.add(edge)
        for (vertex in vertices) {
            if (edge.start == vertex || edge.end == vertex) {
                matrix[vertex]?.put(edge, 1)
            } else {
                matrix[vertex]?.put(edge, 0)
            }
        }
    }

    private fun initVertex(vertex: Vertex<V>) {
        vertices.add(vertex)
        matrix[vertex] = HashMap()
        for (edge in edges) {
            edge?.let { matrix[vertex]?.put(it, 0) }
        }
    }
}
