package ru.nsu.fit.konstantinov.task_2_2_1.employees

import ru.nsu.fit.konstantinov.task_2_2_1.models.DeliveryWorkerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.work.DeliveryWork
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit

class DeliveryWorker(
    deliveryWorkerConfig: DeliveryWorkerConfig?, pizzeriaWork: PizzeriaWork, itemsInStorage: ArrayBlockingQueue<Order>
) : Runnable {
    private val waitingTime = 1000
    var id = 0
    var capacity = 0
    var deliveryTime = 0
    private var bag: MutableList<Order> = mutableListOf()
    private var itemsInStorage: ArrayBlockingQueue<Order>
    private var pizzeriaWork: PizzeriaWork
    private var deliveryWorkers: DeliveryWork? = null

    init {
        if (deliveryWorkerConfig != null) {
            id = deliveryWorkerConfig.id
            deliveryTime = deliveryWorkerConfig.deliveryTime
            capacity = deliveryWorkerConfig.capacity
            bag = ArrayList()
            this.pizzeriaWork = pizzeriaWork
            this.itemsInStorage = itemsInStorage
        } else {
            throw NullPointerException()
        }
    }

    fun setDeliveryWorkers(deliveryWorkers: DeliveryWork?) {
        this.deliveryWorkers = deliveryWorkers
    }

    override fun run() {
        while (!pizzeriaWork.isRestaurantClosed || !(itemsInStorage.size == 0 && pizzeriaWork.areAllBakersFinishedWork())) {
            deliveryWorkers?.lock?.lock()
            try {
                for (i in 0 until capacity) {
                    var order: Order? = null
                    if (bag.size != 0) {
                        try {
                            order = itemsInStorage.poll(waitingTime.toLong(), TimeUnit.MILLISECONDS)
                            if (order == null) {
                                println(
                                    "Delivery Worker #$id is ready to deliver. Another pizza would fit in the bag."
                                )
                                break
                            }
                            bag.add(order)
                            println(
                                ("Delivery Worker #" + id + " picked up the Order #" + order.id + ". He has " + bag.size + " pizza(s) in the bag.")
                            )
                        } catch (e: InterruptedException) {
                            assert(false)
                        }
                    } else {
                        while (bag.size == 0) {
                            if ((pizzeriaWork.isRestaurantClosed && (itemsInStorage.size == 0) && pizzeriaWork.areAllBakersFinishedWork())) {
                                println("We are closed!")
                                pizzeriaWork.closePizzeria()
                                break
                            }
                            order = itemsInStorage.poll(waitingTime.toLong(), TimeUnit.MILLISECONDS)
                            if (order != null) {
                                bag.add(order)
                            }
                        }
                        if (pizzeriaWork.isRestaurantClosed) {
                            break
                        }
                        if (order != null) {
                            println(
                                ("Delivery Worker #" + id + " picked up the Order #" + order.id + ". He has " + bag.size + " pizza(s) in the bag.")
                            )
                        }
                    }
                }
            } catch (e: InterruptedException) {
                assert(false)
            } finally {
                deliveryWorkers?.lock?.unlock()
            }
            if (pizzeriaWork.isRestaurantClosed) {
                break
            }
            try {
                for (order: Order? in bag) {
                    Thread.sleep(deliveryTime.toLong())
                    println("Delivery Worker #$id delivered your pizza.")
                }
                println("Delivery Worker #$id delivered all the orders.")
                Thread.sleep(deliveryTime.toLong())
                bag.clear()
            } catch (e: InterruptedException) {
                assert(false)
            }
        }
        pizzeriaWork.endShiftForDeliveryWorker()
        println("Delivery Worker #$id is done for today.")
    }
}
