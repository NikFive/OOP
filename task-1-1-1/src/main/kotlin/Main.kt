import ru.nsu.fit.konstantinov.task_1_1_1.MyHeapSort.Factory.heapSort

fun main() {
    println("Enter an array of integers in the format: 1 2 3 4")
    val array = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    heapSort(array)
    for (i in array) {
        print(i)
        print(" ")
    }
}
