package ru.nsu.fit.konstantinov.task_2_2_1

import ru.nsu.fit.konstantinov.task_2_2_1.employees.Baker
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Employees
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.utils.JSONReader
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.DeliveryWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.io.File
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Pizzeria(
    bakerFile: File?,
    deliveryFile: File?,
    itemsInStorage: ArrayBlockingQueue<Order>,
    waitingOrders: LinkedBlockingQueue<Order>
) {
    private val employees: Employees
    private val bakers: BakerWork = BakerWork()
    private val deliveryWorkers: DeliveryWork = DeliveryWork()
    private val pizzeriaWork: PizzeriaWork = PizzeriaWork()
    private val waitingOrders: LinkedBlockingQueue<Order>
    private val itemsInStorage: ArrayBlockingQueue<Order>
    private var waitingTimeMilliseconds = 0
    private val workingTime: Long = 3000

    init {
        this.itemsInStorage = itemsInStorage
        this.waitingOrders = waitingOrders
        val jsonReader = JSONReader()
        employees = Employees(
            jsonReader.readBakers(bakerFile),
            jsonReader.readDeliveryWorkers(deliveryFile),
            itemsInStorage,
            waitingOrders,
            pizzeriaWork
        )
        setWainingTime(3000)
    }

    /**
     * Get orders, make them, closes the restaurant, and returns the pizzeriaHeadquarters object.
     *
     * @return the pizzeriaOverview object
     */
    fun start(): PizzeriaWork {
        bakers.run(employees, pizzeriaWork)
        deliveryWorkers.run(employees, pizzeriaWork)
        val curTime = System.currentTimeMillis()
        val t: Thread = object : Thread() {
            override fun run() {
                try {
                    while (curTime + workingTime > System.currentTimeMillis()) {
                        order()
                        sleep(500)
                    }
                } catch (e: InterruptedException) {
                    assert(false)
                }
            }
        }
        t.start()
        Thread.sleep(workingTime)
        closePizzeria()
        return pizzeriaWork
    }

    private fun order() {
        println("Order #" + pizzeriaWork.currentOrderId + ".")
        val order = Order(pizzeriaWork.currentOrderId)
        pizzeriaWork.updateCurrentOrderId()
        waitingOrders.put(order)
    }

    private fun closePizzeria() {
        pizzeriaWork.closePizzeria()
        while (!waitingOrders.isEmpty()) {
            try {
                Thread.sleep(waitingTimeMilliseconds.toLong())
            } catch (e: InterruptedException) {
                assert(false)
            }
        }
        for (getBakersAndPizzas in bakers.getBakersAndPizzas()) {
            val baker: Baker = getBakersAndPizzas.subject as Baker
            if (baker.isWaitingForOrder) {
                getBakersAndPizzas.future.cancel(true)
            }
        }
        while (!pizzeriaWork.areAllBakersFinishedWork() || !pizzeriaWork.areAllDeliveryWorkersFinishedWork()) {
            try {
                Thread.sleep(waitingTimeMilliseconds.toLong())
            } catch (e: InterruptedException) {
                assert(false)
            }
        }
    }

    /**
     * Give you opportunity to set waiting time in pizzeria.
     *
     * @param waitingTime waiting time in pizzeria.
     */
    fun setWainingTime(waitingTime: Int) {
        waitingTimeMilliseconds = waitingTime
    }
}
