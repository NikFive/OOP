package ru.nsu.fit.konstantinov.task_1_2_1

/**
 * Implementation of a regular stack
 *
 * This class allows you to create and use a data structure such as a stack.
 *
 * @param T the type of elements in this stack.
 */

class MyStack<T> {
    /** Field for array of the stack */
    private var stackArray
            : Array<T?> = arrayOfNulls<Any>(1) as Array<T?>

    /** Field for length of the stack */
    private var len = 0

    /** Field for capacity of the stack */
    private var cap = 1

    /**
     * Adds an *elem* to the stack.
     * @param elem The element of the *T* type to add to the stack
     */
    fun push(elem: T) {
        if (len + 1 > cap) {
            cap *= 2
            stackArray = stackArray.copyOf(cap)
        }
        stackArray[len] = elem
        len++
    }

    /**
     * Pulls out an *elem* of the stack.
     * @return The element of the *T* type which needs to be pulled out of the stack
     * @exception IllegalStateException The stack is empty
     */
    fun pop(): T? {
        val result: T?
        if (len > 0) {
            len--
            result = stackArray[len]
            stackArray[len] = null
        } else {
            throw IllegalStateException("The stack is empty")
        }
        return result
    }

    /**
     * Pulls out a stack of *count* length from the stack.
     * @param count the number of elements to pull from the stack
     * @return A new stack consisting of *count* elements of the original stack
     * @exception IllegalStateException The size of the required stack is larger than the original one
     */
    fun popStack(count: Int): MyStack<T?> {
        val result: MyStack<T?> = MyStack()
        if (count <= len) {
            result.stackArray = stackArray.copyOfRange(len - count, len)
            result.len = count
            len -= count
        } else {
            throw IllegalStateException("The stack is empty.")
        }
        return result
    }

    /**
     * Adds another stack to original stack
     * @param elems stack to push
     */
    fun pushStack(elems: MyStack<T>) {
        while ((elems.len + len + 1) > cap) {
            cap *= 2
        }
        stackArray = stackArray.copyOf(cap)
        for (elem in elems.stackArray) {
            stackArray[len] = elem
            len++
        }
    }

    /**
     * Gets size of the stack
     * @return the size of the stack
     */
    fun count(): Int {
        return len
    }
}
