import ru.nsu.fit.konstantinov.task_1_1_1.MyHeapSort.Companion.heapSort

fun main() {
    println("Enter an array of integers in the format: 1 2 3 4")
    readLine()?.split(" ")?.map { it.toInt() }?.toIntArray()?.let {
        heapSort(it)
        for (i in it) {
            print(i)
            print(" ")
        }
    }
}
