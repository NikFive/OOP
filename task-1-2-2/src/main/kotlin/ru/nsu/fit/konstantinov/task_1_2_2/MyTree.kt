package ru.nsu.fit.konstantinov.task_1_2_2

class MyTree<T>(value: T? = null) : Collection<T> {
    private var root: Node<T>? = null

    init {
        this.root
        if (value == null) {
            this.root = null
        } else {
            this.root = Node(value)
        }
    }

    fun add(node: Node<T>, value: T): Node<T> {
        val newElem = Node(value)
        node.children.add(newElem)
        return newElem
    }

    fun add(value: T): Node<T> {
        val newElem = Node(value)
        if (root == null) {
            root = newElem
        } else {
            root?.children?.add(newElem)
        }
        return newElem
    }

    fun addAll(c: Collection<T>): Boolean {
        for (elem in c) {
            add(elem)
        }
        return true
    }

    fun addAll(node: Node<T>?, c: Collection<T>): Boolean {
        for (elem in c) {
            if (node != null) {
                add(node, elem)
            } else {
                return false
            }
        }
        return true
    }

    fun remove(node: Node<T>, o: T): Boolean {
        var res = false
        for (i in node.children) {
            if (i.value == o) {
                for (j in i.children) {
                    add(node, j.value)
                }
                node.children.remove(i)
                res = true
                break
            }
        }
        return res
    }

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = root?.children?.size ?: 0

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean = root == null

    override fun iterator(): Iterator<T> = DFSIterator(root)

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
        root?.forEachDepthFirst {
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
        override fun hasNext(): Boolean = !nodesToVisit.isEmpty()

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): T {
            return nextNode().value
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
