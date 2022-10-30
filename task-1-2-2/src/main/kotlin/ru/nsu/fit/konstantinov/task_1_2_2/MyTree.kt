package ru.nsu.fit.konstantinov.task_1_2_2

/**
 * A tree containing nodes that are represented as MutableList.
 *
 * @param T could be any type.
 * @param value can set the value of the tree. The default value is null.
 */
class MyTree<T>(value: T? = null) : Collection<T> {
    private var state = 0
    private var root: Node<T>? = null

    /**
     * Constructor of the tree.
     * Create an empty tree or tree with root with value.
     */
    init {
        this.root = value?.let { Node(it) }
    }

    /**
     * Add a son with *value* to the *node*.
     *
     * @param node the node to add the son to.
     * @param value the value to be assigned to the son.
     * @return added son
     */
    fun add(node: Node<T>, value: T): Node<T> {
        state++
        val newElem = Node(value)
        node.children.add(newElem)
        return newElem
    }

    /**
     * Add a son with *value* to the root. If the tree is empty, then sets the value to the root.
     *
     * @param value the value to be assigned to the son.
     * @return added son
     */
    fun add(value: T): Node<T> {
        state++
        val newElem = Node(value)
        if (root == null) {
            root = newElem
        } else {
            root?.children?.add(newElem)
        }
        return newElem
    }

    /**
     * Add a *collection* to the root.
     *
     * @param collection collection to add.
     * @return returns true if all the elements of the collection are successfully added.
     */
    fun addAll(collection: Collection<T>): Boolean {
        for (elem in collection) {
            add(elem)
        }
        return true
    }

    /**
     * Add a *collection* to the *node*.
     *
     * @param collection collection to add.
     * @param node the node to add the collection to.
     * @return returns true if all the elements of the collection are successfully added.
     */
    fun addAll(node: Node<T>?, collection: Collection<T>): Boolean {
        if (node == null) {
            return false
        }
        for (elem in collection) {
            add(node, elem)
        }
        return true
    }

    /**
     * Removes the son of the root with the *value*. The son's children go to the root.
     *
     * @param value the value to be deleted.
     * @return returns true if the element deleted successfully.
     */
    fun remove(value: T): Boolean = remove(root, value)


    /**
     * Removes the son of the *node* with the *value*. The son's children go to the *node*.
     *
     * @param value the value to be deleted.
     * @return returns true if the element deleted successfully.
     */
    fun remove(node: Node<T>?, value: T): Boolean {
        state++
        if (node == null) {
            return false
        }
        var res = false
        for (i in node.children) {
            if (i.value == value) {
                var array = emptyArray<Node<T>>()
                for (j in i.children) {
                    array += j
                }
                node.children += array
                node.children.remove(i)
                res = true
                break
            }
        }
        return res
    }

    /**
     * Removes the sons from *tree* of the root with the *value*. The son's children go to the root.
     *
     * @param collection the collection to be deleted.
     * @return returns true if all the elements deleted successfully.
     */
    fun removeAll(collection: Collection<T>): Boolean {
        var deleteFlag = true
        for (i in collection) {
            if (!remove(i)) {
                deleteFlag = false
            }
        }
        return deleteFlag
    }

    /**
     * Deletes the tree.
     */
    fun clear() {
        root?.children?.clear()
        root = null
        state++
    }

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = root?.sizeOfSubTree() ?: 0

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     *
     * @return returns `true` if the collection is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean = root == null

    /**
     * DFS Iterator on the tree.
     *
     * @return returns an iterator type object.
     */
    override fun iterator(): Iterator<T> = DFSIterator(this)

    /**
     * BFS Iterator on the tree.
     *
     * @return returns an iterator type object.
     */
    fun iteratorBFS(): Iterator<T> = BFSIterator(this)

    /**
     * Checks if all elements in the specified collection are contained in this tree.
     *
     * @param elements elements which we need to check.
     * @return returns true if the all elements contains in the tree.
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
     * Checks if the specified element is contained in this tree.
     *
     * @param element element which we need to check.
     * @return returns true if the element contains in the tree.
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

    /**
     * The class that provides an DFS iterator for traversing the tree
     */
    private class DFSIterator<T>(val tree: MyTree<T>) : Iterator<T> {
        private var nodesToVisit: ArrayDeque<Node<T>> = ArrayDeque()
        val currentState = tree.state

        /**
         * The constructor that add root to the Deque.
         */
        init {
            tree.root?.let { nodesToVisit.add(it) }
        }

        /**
         * Returns `true` if the iteration has more elements.
         *
         * @return `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean = !nodesToVisit.isEmpty()

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         */
        override fun next(): T = nextNode().value

        private fun addToQueue(node: Node<T>?) {
            if (node != null) {
                for (i in node.children.size - 1 downTo 0) {
                    nodesToVisit.addFirst(node.children[i])
                }
            }
        }

        /**
         * Returns the next node in the dequeue.
         *
         * @return the next node in the dequeue.
         */
        private fun nextNode(): Node<T> {
            if (currentState != this.tree.state) {
                throw ConcurrentModificationException("You cannot iterate after changing your tree")
            }
            check(hasNext())
            val newNode = nodesToVisit.removeFirst()
            addToQueue(newNode)
            return newNode
        }
    }

    /**
     * The class that provides an BFS iterator for traversing the tree
     */
    private class BFSIterator<T>(val tree: MyTree<T>) : Iterator<T> {
        private var nodesToVisit: ArrayDeque<Node<T>> = ArrayDeque()
        val currentState = tree.state

        /**
         * The constructor that add root to the Deque.
         */
        init {
            tree.root?.let { nodesToVisit.add(it) }
        }

        /**
         * Returns `true` if the iteration has more elements.
         *
         * @return `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean = !nodesToVisit.isEmpty()

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         */
        override fun next(): T = nextNode().value

        private fun addToQueue(node: Node<T>?) {
            node?.let { nodesToVisit.addAll(node.children) }
        }

        /**
         * Returns the next node in the dequeue.
         *
         * @return the next node in the dequeue.
         */
        private fun nextNode(): Node<T> {
            if (currentState != this.tree.state) {
                throw ConcurrentModificationException("You cannot iterate after changing your tree")
            }
            check(hasNext())
            val newNode = nodesToVisit.removeFirst()
            addToQueue(newNode)
            return newNode
        }
    }

    /**
     * The class that is a node of the tree.
     *
     * @param T could be any type.
     * @param value the value to be written to the node.
     */
    class Node<T>(val value: T) {
        /**
         * MutableList of node children
         */
        val children: MutableList<Node<T>> = mutableListOf()

        /**
         * Traversing the tree in depth.
         *
         * @param visit parameter for DFS iterations.
         */
        fun forEachDepthFirst(visit: (Node<T>) -> Unit) {
            visit(this)
            children.forEach {
                it.forEachDepthFirst(visit)
            }
        }

        /**
         * Returns the size of the subtree with the *node* root
         *
         * @return the size of the subtree with the *node* root
         */
        fun sizeOfSubTree(): Int {
            var res = 0
            forEachDepthFirst { res += 1 }
            return res
        }
    }
}
