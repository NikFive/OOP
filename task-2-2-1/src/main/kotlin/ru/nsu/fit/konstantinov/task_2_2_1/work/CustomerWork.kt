package ru.nsu.fit.konstantinov.task_2_2_1.work

import ru.nsu.fit.konstantinov.task_2_2_1.persons.Customer

import ru.nsu.fit.konstantinov.task_2_2_1.pizzeria.Pizzeria
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CustomerWork(private val pizzeria: Pizzeria) : Work{
    private val minCustomerCount = 2
    private val customers: MutableList<Runnable> = arrayListOf()
    private val executorService: ExecutorService = Executors.newFixedThreadPool(minCustomerCount)

    override fun run() {
        for (i in 0 until (Random().nextInt(2) + minCustomerCount)) {
            val customer = Customer(pizzeria, Random().nextInt(2) + 1)
            customers.add(customer)
        }
        customers.forEach(executorService::execute)
    }

    override fun stopWork() {
        executorService.shutdownNow()
    }
}
