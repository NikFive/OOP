package ru.nsu.fit.konstantinov.task_1_2_2

typealias Visitor<T> = (MyTreeNode<T>) -> Unit

class MyTreeNode<T>(val value: T) {
    private val children: MutableList<MyTreeNode<T>> = mutableListOf()
    fun add(child: MyTreeNode<T>) = children.add(child)
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun search(value: T): T? {
        var result: MyTreeNode<T>? = null
        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result?.value
    }
}
