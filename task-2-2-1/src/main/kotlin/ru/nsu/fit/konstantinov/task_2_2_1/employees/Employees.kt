package ru.nsu.fit.konstantinov.task_2_2_1.employees

import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.DeliveryWorkerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.Order
import ru.nsu.fit.konstantinov.task_2_2_1.work.PizzeriaWork
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Employees(
    bakerConfigs: List<BakerConfig>,
    deliveryWorkersConfig: List<DeliveryWorkerConfig?>,
    itemsInStorage: ArrayBlockingQueue<Order>,
    waitingOrders: LinkedBlockingQueue<Order>,
    pizzeriaWork: PizzeriaWork
) {
    private val bakers = LinkedList<Baker>()
    private val deliveryWorkers = LinkedList<DeliveryWorker>()
    private val pizzeriaWork: PizzeriaWork

    init {
        this.pizzeriaWork = pizzeriaWork
        for (bakerConfig in bakerConfigs) {
            bakers.add(Baker(bakerConfig, waitingOrders, pizzeriaWork, itemsInStorage))
        }
        for (deliveryWorkerConfig in deliveryWorkersConfig) {
            deliveryWorkers.add(
                DeliveryWorker(deliveryWorkerConfig, pizzeriaWork, itemsInStorage)
            )
        }
    }

    val numberOfBakers: Int
        get() = bakers.size
    val numberOfDeliveryWorkers: Int
        get() = deliveryWorkers.size

    fun getBakers(): List<Baker> = Collections.unmodifiableList(bakers)

    fun getDeliveryWorkers(): List<DeliveryWorker> = Collections.unmodifiableList(deliveryWorkers)
}
