package ru.nsu.fit.konstantinov.task_2_2_1.employees

import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.work.BakerWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Baker(
    bakerConfig: BakerConfig?,
    waitingOrders: LinkedBlockingQueue<Order>,
    pizzeriaWork: PizzeriaWork,
    itemsInStorage: ArrayBlockingQueue<Order>
) : Runnable {
    var id = 0
    var cookingTime = 0
    var isWaitingForOrder = false
    private var waitingOrders: LinkedBlockingQueue<Order>
    private var pizzeriaWork: PizzeriaWork
    private var bakers: BakerWork? = null
    private var itemsInStorage: ArrayBlockingQueue<Order>? = null

    init {
        if (bakerConfig != null) {
            id = bakerConfig.id
            cookingTime = bakerConfig.cookingTime
            isWaitingForOrder = false
            this.itemsInStorage = itemsInStorage
            this.waitingOrders = waitingOrders
            this.pizzeriaWork = pizzeriaWork
        } else {
            throw NullPointerException()
        }
    }

    fun setBakers(bakers: BakerWork?) {
        this.bakers = bakers
    }

    override fun run() {
        while (!pizzeriaWork.isRestaurantClosed || !waitingOrders.isEmpty()) {
            var currentOrder: Order? = null
            try {
                currentOrder = waitingOrders.take()
                isWaitingForOrder = true
                bakers?.lock?.lock()
                if (pizzeriaWork.isRestaurantClosed && waitingOrders.isEmpty()) {
                    break
                }
                isWaitingForOrder = false
                Thread.sleep(cookingTime.toLong())
            } catch (e: InterruptedException) {
                assert(false)
            } finally {
                bakers?.lock?.unlock()
            }
            if (currentOrder != null) {
                println(
                    "Baker #" + id + " is making a pizza. Order #" + currentOrder.id + "."
                )
                println(
                    "Baker #" + id + " finished making a pizza. Order #" + currentOrder.id + "."
                )
                println("Baker #$id put pizza in the storage.")
                try {
                    itemsInStorage?.put(currentOrder)
                } catch (e: InterruptedException) {
                    assert(false)
                }
            }
        }
        pizzeriaWork.endShiftForBaker()
        println("Baker #$id finished his work for today.")
    }
}
