package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Warehouse(private val capacity: Int) {
    private val pizzas: ArrayDeque<Order> = ArrayDeque(capacity)

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    @Synchronized
    fun addPizza(order: Order) {
        while (isFull()) {
            lock.withLock {
                condition.await()
            }
        }
        pizzas.add(order)
        lock.withLock {
            condition.signalAll()
        }
    }

    val pizza: Order
        @Synchronized
        get() {
            while (isEmpty()) {
                lock.withLock {
                    condition.await()
                }
            }
            lock.withLock {
                condition.signalAll()
            }
            return pizzas.removeFirst()
        }

    fun isFull(): Boolean = pizzas.size == capacity
    fun isEmpty(): Boolean = pizzas.size == 0
}
