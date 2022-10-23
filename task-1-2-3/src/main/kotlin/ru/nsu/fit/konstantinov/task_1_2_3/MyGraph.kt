package ru.nsu.fit.konstantinov.task_1_2_3

class MyGraph<T> {
    private data class Vertex<T>(val name: T) {
        val neighbors = mutableSetOf<Vertex<T>>()
    }

    private val vertices = mutableMapOf<T, Vertex<T>>()
    private fun connect(first: Vertex<T>?, second: Vertex<T>?) {
        if (second != null) first?.neighbors?.add(second)
        if (first != null) second?.neighbors?.add(first)
    }

    fun addVertex(name: T) {
        vertices[name] = Vertex(name)
    }

    fun connect(first: T, second: T) = connect(vertices[first], vertices[second])

    fun neighbors(name: T): List<T> = vertices[name]?.neighbors?.map { it.name } ?: listOf()

    fun dfs(start: T, finish: T) = dfs(start, finish, setOf())

    private fun dfs(start: T, finish: T, visited: Set<T>): Int = if (start == finish) 0
    else {
        val min = neighbors(start).filter {
            it !in visited
        }.mapNotNull {
            dfs(it, finish, visited + start)
        }.min()
        min + 1
    }

    fun bfs(start: T, finish: T): Int {
        val queue = ArrayDeque<T>()
        queue.add(start)
        val visited = mutableMapOf(start to 0)
        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            val distance = visited[next]!!
            if (next == finish) return distance
            for (neighbor in neighbors(next)) {
                if (neighbor in visited) continue
                visited[neighbor] = distance + 1
                queue.add(neighbor)
            }
        }
        return -1
    }
}
