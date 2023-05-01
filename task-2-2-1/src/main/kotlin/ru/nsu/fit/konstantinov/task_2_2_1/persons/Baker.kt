package ru.nsu.fit.konstantinov.task_2_2_1.persons

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.Warehouse
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderGetter
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Pizza

class Baker(private val warehouse: Warehouse, private val orderGetter: OrderGetter, private val cookingTime: Int) :
    Runnable {
    override fun run() {
        while (true) {
            try {
                println("Baker is running: $this")
                val order = orderGetter.order.apply { pizza = cookPizza(this) }
                warehouse.addPizza(order)
            } catch (e: InterruptedException) {
                println("Baker was interrupted")
                return
            }
        }
    }
    private fun cookPizza(order: Order): Pizza {
        Thread.sleep(cookingTime.toLong())
        val pizza = Pizza()
        println("Baker $this are cooking pizza for order ${order.id}")
        return pizza
    }
}
