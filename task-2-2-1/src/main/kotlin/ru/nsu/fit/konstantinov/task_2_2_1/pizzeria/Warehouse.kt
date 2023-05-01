package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import ru.nsu.fit.konstantinov.task_2_2_1.utils.notifyAll
import ru.nsu.fit.konstantinov.task_2_2_1.utils.wait

class Warehouse(private val capacity: Int) {
    private val pizzas: ArrayDeque<Order> = ArrayDeque(capacity)

    @Synchronized
    fun addPizza(order: Order) {
        while (isFull()) {
            this.wait()
        }
        pizzas.add(order)
        notifyAll()
    }

    val pizza: Order
        @Synchronized
        get() {
            while (isEmpty()) {
                this.wait()
            }
            notifyAll()
            return pizzas.removeFirst()
        }

    fun isFull(): Boolean = pizzas.size == capacity
    fun isEmpty(): Boolean = pizzas.size == 0
}
