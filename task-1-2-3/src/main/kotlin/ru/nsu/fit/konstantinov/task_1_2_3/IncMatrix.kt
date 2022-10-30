package ru.nsu.fit.konstantinov.task_1_2_3

class IncMatrix<V, E> : Graph<V, E> {
    override var vertexes: MutableSet<Vertex<V>?>? = HashSet()
    override var edges: MutableSet<Edge<V, E>?>? = HashSet()
    override val vertexNumber: Int
        get() = vertexes?.size ?: 0
    override val edgesNumber: Int
        get() = edges?.size ?: 0
    private var matrix: HashMap<Vertex<V>, HashMap<Edge<V, E>, Int>>? = HashMap()

    override fun addVertex(vertex: Vertex<V>?): Boolean {
        return if (vertexes?.contains(vertex)?.not() == true) {
            vertex?.let { initVertex(it) }
            true
        } else {
            false
        }
    }

    override fun deleteVertex(vertex: Vertex<V>?) {
        val toDelete: ArrayList<Edge<V, E>> = ArrayList()
        vertexes?.remove(vertex)
        matrix?.remove(vertex)
        edges?.forEach { edge ->
            edge?.let {
                if (it.start == vertex || it.end == vertex) {
                    toDelete.add(it)
                    for (customVertex in vertexes!!) {
                        matrix?.get(customVertex)?.remove(it)
                    }
                }
            }
        }
        toDelete.forEach { edge -> edges?.remove(edge) }
    }

    override fun addEdge(edge: Edge<V, E>?) {
        edge?.let {
            if (vertexes?.contains(it.end)?.not() == true || vertexes?.contains(it.start)?.not() == true) {
                throw IllegalArgumentException("Vertexes does not in the graph.")
            }
            initEdge(it)
        }
    }

    override fun deleteEdge(edge: Edge<V, E>?) {
        edges?.remove(edge)
        vertexes?.forEach { vertex ->
            matrix?.get(vertex)?.remove(edge)
        }
    }

    override fun getAdjVertexes(vertex: Vertex<V>?): MutableSet<Vertex<V>> {
        val vertexSet: MutableSet<Vertex<V>> = HashSet()
        edges?.forEach { edge ->
            edge?.let {
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

    override fun getVertexDegree(vertex: Vertex<V>?): Int = getAdjVertexes(vertex).size

    override fun setVertexElement(vertex: Vertex<V>?, newElem: V) {
        vertex?.let {
            it.elem = newElem
        }
    }

    private fun initEdge(edge: Edge<V, E>) {
        edges?.add(edge)
        vertexes?.forEach { vertex ->
            if (edge.start == vertex || edge.end == vertex) {
                matrix?.get(vertex)?.set(edge, 1)
            } else {
                matrix?.get(vertex)?.set(edge, 0)
            }
        }
    }

    private fun initVertex(vertex: Vertex<V>) {
        vertexes?.add(vertex)
        matrix?.set(vertex, HashMap())
        edges?.forEach { edge ->
            edge?.let { matrix?.get(vertex)?.put(it, 0) }
        }
    }
}
