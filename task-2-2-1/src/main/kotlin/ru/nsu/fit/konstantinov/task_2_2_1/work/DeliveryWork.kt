package ru.nsu.fit.konstantinov.task_2_2_1.work

import ru.nsu.fit.konstantinov.task_2_2_1.employees.DeliveryWorker
import ru.nsu.fit.konstantinov.task_2_2_1.employees.Employees
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class DeliveryWork {
    val lock: Lock
    private val orders: MutableList<Future<*>>

    init {
        orders = ArrayList()
        lock = ReentrantLock(true)
    }

    fun getOrders(): List<Future<*>> = orders
    fun run(employees: Employees, pizzeriaWork: PizzeriaWork) {
        pizzeriaWork.setNumberOfDeliveryWorkers(employees.numberOfDeliveryWorkers)
        val executor = Executors.newFixedThreadPool(employees.numberOfDeliveryWorkers)
        val deliveryWorkers: List<DeliveryWorker> = employees.getDeliveryWorkers()
        for (deliveryWorker in deliveryWorkers) {
            deliveryWorker.setDeliveryWorkers(this)
            val future: Future<*> = executor.submit(deliveryWorker)
            orders.add(future)
        }
    }
}
