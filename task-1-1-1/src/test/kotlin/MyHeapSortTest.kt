import kotlin.test.*

internal class MyHeapSortTest {
    private val testHeapSort: MyHeapSort = MyHeapSort()
    @Test
    fun test_one() {
        val input = intArrayOf(5, 4, 3, 2, 1)
        val answer = intArrayOf(1, 2, 3, 4, 5)
        testHeapSort.heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun test_two() {
        val input = intArrayOf()
        val answer = intArrayOf()
        testHeapSort.heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun test_three() {
        val input = intArrayOf(1, 1, 1, 1)
        val answer = intArrayOf(1, 1, 1, 1)
        testHeapSort.heapSort(input)
        assertContentEquals(answer, input)
    }
}
