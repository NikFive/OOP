import org.junit.jupiter.api.Test
import ru.nsu.fit.konstantinov.task_1_1_1.MyHeapSort.Factory.heapSort
import kotlin.test.assertContentEquals

class MyHeapSortTest {
    @Test
    fun testStandard() {
        val input = intArrayOf(101, 351, 166, 767, 923, 971, 524, 695, 8, 61)
        val answer = intArrayOf(8, 61, 101, 166, 351, 524, 695, 767, 923, 971)
        heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun testReverse() {
        val input = intArrayOf(5, 4, 3, 2, 1)
        val answer = intArrayOf(1, 2, 3, 4, 5)
        heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun testEmpty() {
        val input = intArrayOf()
        val answer = intArrayOf()
        heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun testEqual() {
        val input = intArrayOf(1, 1, 1, 1)
        val answer = intArrayOf(1, 1, 1, 1)
        heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun testZero() {
        val input = intArrayOf(0)
        val answer = intArrayOf(0)
        heapSort(input)
        assertContentEquals(answer, input)
    }

    @Test
    fun testNegative() {
        val input = intArrayOf(-1, 5, -1, 5, 0, 0, -1, 5, -1, 5, 0, 0, -1, 5, -1, 5, 0, 0)
        val answer = intArrayOf(-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5)
        heapSort(input)
        assertContentEquals(answer, input)
    }
}
