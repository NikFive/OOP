package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria

import ru.nsu.fit.konstantinov.task_2_2_1.models.ConfigModel
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Courier
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderCreator
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderGetter
import ru.nsu.fit.konstantinov.task_2_2_1.utils.notifyAll
import ru.nsu.fit.konstantinov.task_2_2_1.utils.wait
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.CourierWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.CustomerWork

class Pizzeria(configModel: ConfigModel) : Runnable, OrderGetter, OrderCreator {
    private val orders: ArrayDeque<Order> = ArrayDeque()
    private val customerWork = CustomerWork(this)
    val warehouse = Warehouse(configModel.warehouse.capacity)
    val bakerWork = BakerWork(ArrayList<Baker>().apply {
        for (bakerModel in configModel.bakers) {
            val baker = Baker(warehouse, this@Pizzeria, bakerModel.cookingTime)
            this.add(baker)
        }
    })
    val courierWork = CourierWork(ArrayList<Courier>().apply {
        for (courierModel in configModel.couriers) {
            val courier = Courier(warehouse, courierModel.bagCapacity, courierModel.deliveryTime)
            this.add(courier)
        }
    })

    override fun run() {
        bakerWork.run()
        courierWork.run()
        customerWork.run()
    }

    @Synchronized
    fun forceClosing() {
        customerWork.stopWork()
    }

    @Synchronized
    fun gracefulClosing() {
        customerWork.stopWork()
        bakerWork.stopWork()
        courierWork.stopWork()
        notifyAll()
    }

    private var orderNumber = 0

    @Synchronized
    override fun createOrder(pizzaCount: Int) {
        val order = Order(orderNumber++, pizzaCount).apply {
            customer = Runnable { println("Order ${order.id} was successfully delivered") }
        }
        orders.add(order)
        println("Pizzeria received order with number ${order.id}")
        notifyAll()
    }

    override val order: Order
        @Synchronized get() {
            while (orders.isEmpty()) {
                wait()
            }
            return orders.removeFirst()
        }

    override val isNoOrders: Boolean
        get() = orders.isEmpty()
}
