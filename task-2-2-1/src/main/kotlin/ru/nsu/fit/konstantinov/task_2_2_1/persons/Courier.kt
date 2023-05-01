package ru.nsu.fit.konstantinov.task_2_2_1.persons

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.Warehouse
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order


class Courier(private val warehouse: Warehouse, private val bagCapacity: Int, private val deliveryTime: Int) :
    Runnable {
    private val bag: ArrayDeque<Order> = ArrayDeque()
    override fun run() {
        while (true) {
            try {
                println("Courier is running: $this")
                deliverPizza()
            } catch (e: InterruptedException) {
                println("Courier was interrupted")
                return
            }
        }
    }
    private fun deliverPizza() {
        while (bag.size != bagCapacity) {
            val order = warehouse.pizza
            bag.add(order)
            println("Courier $this received pizza $order")
        }
        for (order in bag) {
            Thread.sleep(deliveryTime.toLong())
            order.customer?.run()
            bag.remove(order)
        }
    }
}
