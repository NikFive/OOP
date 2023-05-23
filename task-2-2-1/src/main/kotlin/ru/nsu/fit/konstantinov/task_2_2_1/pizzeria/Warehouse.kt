package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Warehouse(private val capacity: Int) {
    private val pizzas: ArrayDeque<Order> = ArrayDeque(capacity)

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()


    fun addPizza(order: Order) {
        lock.withLock {
            while (isFull()) {
                condition.await()
            }
            pizzas.add(order)
            condition.signalAll()
        }
    }

    val pizza: Order
        get() {
            lock.withLock {
                while (isEmpty()) {
                    condition.await()
                }
                condition.signalAll()
                return pizzas.removeFirst()
            }
        }

    fun isFull(): Boolean = pizzas.size == capacity
    fun isEmpty(): Boolean = pizzas.size == 0
}
