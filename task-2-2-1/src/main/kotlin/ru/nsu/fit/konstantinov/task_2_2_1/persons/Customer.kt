package ru.nsu.fit.konstantinov.task_2_2_1.persons

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderCreator
import java.util.*

class Customer(private val orderCreator: OrderCreator, private val pizzaCountInOrder: Int) : Runnable {
    override fun run() {
        while (true) {
            println("Customer is running: $this")
            try {
                orderCreator.createOrder(pizzaCountInOrder)
                val minSleepTime = 100
                val sleepTime: Int = Random().nextInt(1000) + minSleepTime
                Thread.sleep(sleepTime.toLong())
            } catch (e: InterruptedException) {
                println("Customer $this was interrupted")
                return
            }
        }
    }
}
