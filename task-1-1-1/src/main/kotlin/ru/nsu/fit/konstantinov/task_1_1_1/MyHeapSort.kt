package ru.nsu.fit.konstantinov.task_1_1_1

/**
 * A group sorted by heap sorting.
 *
 * This class implements an interface for using heap sorting.
 *
 */
class MyHeapSort {
    /**
     * Companion object for simplifying the interface of interaction with heap sorting.
     */
    companion object {
        /**
         * Sorts an array of integers using heap sorting.
         * @param array array of integers to sort.
         */
        fun heapSort(array: IntArray) {
            val length = array.size
            for (i in length - 1 downTo 0) {
                siftDown(array, length, i)
            }
            for (i in length - 1 downTo 0) {
                val temp = array[0]
                array[0] = array[i]
                array[i] = temp
                siftDown(array, i, 0)
            }
        }

        private fun siftDown(array: IntArray, n: Int, i: Int) {
            var max = i
            val leftChild = 2 * i + 1
            val rightChild = 2 * i + 2
            if (leftChild < n && array[leftChild] > array[max]) {
                max = leftChild
            }
            if (rightChild < n && array[rightChild] > array[max]) {
                max = rightChild
            }
            if (max != i) {
                val temp = array[i]
                array[i] = array[max]
                array[max] = temp
                siftDown(array, n, max)
            }
        }
    }
}
