package ru.nsu.fit.konstantinov.task_1_2_2

class MyTree<T>(value: T) : Collection<T> {
    var root: Node<T>

    init {
        this.root = Node(value)
    }

    fun add(node: Node<T>, element: T) {
        val newElem = Node(element)
        node.children.add(newElem)
    }

    fun add(element: T): Node<T> {
        val newElem = Node(element)
        root.children.add(newElem)
        return newElem
    }

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = root.children.size

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean {
        return root.value == null
    }

    override fun iterator(): Iterator<T> {
        return DFSIterator(root)
    }

    /**
     * Checks if all elements in the specified collection are contained in this collection.
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        for (i in elements) {
            if (!contains(i)) {
                return false
            }
        }
        return true
    }

    /**
     * Checks if the specified element is contained in this collection.
     */
    override fun contains(element: T): Boolean {
        var result: Node<T>? = null
        root.forEachDepthFirst {
            if (it.value == element) {
                result = it
            }
        }
        return result != null
    }

    private class DFSIterator<T>(root: Node<T>?) : Iterator<T> {
        var nodesToVisit: ArrayDeque<Node<T>> = ArrayDeque()

        init {
            root?.forEachDepthFirst { nodesToVisit.add(it) }
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return !nodesToVisit.isEmpty()
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): T {
            return this.nextNode().value
        }

        fun nextNode(): Node<T> {
            check(hasNext())
            return nodesToVisit.removeFirst()
        }

    }

    class Node<T>(val value: T) {
        val children: MutableList<Node<T>> = mutableListOf()
        fun forEachDepthFirst(visit: (Node<T>) -> Unit) {
            visit(this)
            children.forEach {
                it.forEachDepthFirst(visit)
            }
        }
    }
}
