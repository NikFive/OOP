package ru.nsu.fit.konstantinov.task_2_2_1.pizzeria

import ru.nsu.fit.konstantinov.task_2_2_1.models.ConfigModel
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.persons.Courier
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.Order
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderCreator
import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.order.OrderGetter
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.CourierWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.CustomerWork
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

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

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    override fun run() {
        bakerWork.run()
        courierWork.run()
        customerWork.run()
    }

    fun forceClosing() {
        lock.withLock {
            customerWork.stopWork()
        }
    }

    fun gracefulClosing() {
        lock.withLock {
            customerWork.stopWork()
            bakerWork.stopWork()
            courierWork.stopWork()

            condition.signalAll()
        }
    }

    private var orderNumber = 0

    override fun createOrder(pizzaCount: Int) {
        lock.withLock {
            val order = Order(orderNumber++, pizzaCount).apply {
                customer = Runnable { println("Order ${getOrder().id} was successfully delivered") }
            }
            orders.add(order)
            println("Pizzeria received order with number ${order.id}")
            condition.signalAll()
        }
    }

    override fun getOrder(): Order {
        lock.withLock {
            while (orders.isEmpty()) {
                condition.await()

            }
            return orders.removeFirst()
        }
    }

    override fun isNoOrders(): Boolean = orders.isEmpty()
}
